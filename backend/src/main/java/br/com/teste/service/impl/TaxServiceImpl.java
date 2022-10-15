package br.com.teste.service.impl;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.exception.impl.BadRequestException;
import br.com.teste.service.TaxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class TaxServiceImpl implements TaxService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaxServiceImpl.class);

    public String transactionType(SchedulingRequest schedulingRequest) {
        long differenceDays = getDays(schedulingRequest);

        if (Objects.equals(schedulingRequest.getTransactionType(), "A")
                && differenceDays == 0) {
            return "A";
        }

        if (Objects.equals(schedulingRequest.getTransactionType(), "B")
                && differenceDays > 0 && differenceDays <= 10) {
                return "B";
        }

        if (Objects.equals(schedulingRequest.getTransactionType(), "C")
                && differenceDays > 10){
                return "C";
        }

        if (Objects.equals(schedulingRequest.getTransactionType(), "D")) {
            return "D";
        } else {
            throw new BadRequestException("Não há taxa aplicável conforme dados passados. Por favor, tente novamente.");
        }
    }

    public long getDays(SchedulingRequest schedulingRequest) {
        final LocalDate scheduleDate = schedulingRequest.getScheduleDate();
        final LocalDate transferDate = LocalDate.now();

        long scheduleDay = scheduleDate.toEpochDay();
        long transferDay = transferDate.toEpochDay();

        return scheduleDay - transferDay;
    }

}
