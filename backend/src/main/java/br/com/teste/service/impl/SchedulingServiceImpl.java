package br.com.teste.service.impl;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.api.dto.SchedulingResponse;
import br.com.teste.domain.Scheduling;
import br.com.teste.domain.Tax;
import br.com.teste.exception.impl.TechnicalException;
import br.com.teste.repository.SchedulingRepository;
import br.com.teste.service.SchedulingService;
import br.com.teste.service.transaction.TransactionTypeContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchedulingServiceImpl implements SchedulingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SchedulingServiceImpl.class);

    private final TransactionTypeContext transactionTypeContext;

    private final SchedulingRepository repository;

    public SchedulingServiceImpl(TransactionTypeContext transactionTypeContext, SchedulingRepository repository) {
        this.transactionTypeContext = transactionTypeContext;
        this.repository = repository;
    }

    public SchedulingResponse saveScheduleTransfer(SchedulingRequest schedulingRequest) {
        Scheduling scheduling = new Scheduling();

        scheduling.setOriginAccount(schedulingRequest.getOriginAccount());
        scheduling.setDestinationAccount(schedulingRequest.getDestinationAccount());
        scheduling.setTransferDate(LocalDate.now());
        scheduling.setScheduleDate(schedulingRequest.getScheduleDate());
        scheduling.setTransferValue(schedulingRequest.getValue());
        scheduling.setTax(calculateTax(schedulingRequest));

        try {
            repository.save(scheduling);

            return new SchedulingResponse(scheduling.getOriginAccount(),
                    scheduling.getDestinationAccount(),
                    scheduling.getTransferDate(),
                    scheduling.getScheduleDate(),
                    scheduling.getTransferValue(),
                    scheduling.getTax().getTaxValue(),
                    scheduling.getTax().getTransactionType());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
            throw new TechnicalException("Não foi possível salvar agendamento. Por favor, tente novamente.", ex);
        }
    }

    private Tax calculateTax(SchedulingRequest schedulingRequest) {
        return transactionTypeContext
                .getTransactionTypeStrategy(schedulingRequest)
                .execute(schedulingRequest);
    }

    public List<SchedulingResponse> findAllSchedules() {
        try {
            return repository.findAll().stream().map(scheduling -> new SchedulingResponse(scheduling.getOriginAccount(),
                    scheduling.getDestinationAccount(),
                    scheduling.getTransferDate(),
                    scheduling.getScheduleDate(),
                    scheduling.getTransferValue(),
                    scheduling.getTax().getTaxValue(),
                    scheduling.getTax().getTransactionType())).collect(Collectors.toList());
        } catch (Exception ex) {
            throw new TechnicalException("Não foi possível obter os agendamentos salvos. Por favor, tente novamente.",
                    ex);
        }
    }

}
