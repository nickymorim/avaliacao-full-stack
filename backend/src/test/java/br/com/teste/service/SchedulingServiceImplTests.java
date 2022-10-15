package br.com.teste.service;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.api.dto.SchedulingResponse;
import br.com.teste.domain.Scheduling;
import br.com.teste.domain.Tax;
import br.com.teste.exception.impl.TechnicalException;
import br.com.teste.repository.SchedulingRepository;
import br.com.teste.service.impl.SchedulingServiceImpl;
import br.com.teste.service.transaction.TransactionTypeContext;
import br.com.teste.service.transaction.strategy.TransactionTypeStrategy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class SchedulingServiceImplTests {

    private TransactionTypeContext transactionTypeContext;

    private TransactionTypeStrategy transactionTypeStrategy;

    private SchedulingRepository repository;

    private SchedulingServiceImpl service;

    @BeforeEach
    void setup() {
        transactionTypeContext = mock(TransactionTypeContext.class);
        transactionTypeStrategy = mock(TransactionTypeStrategy.class);
        repository = mock(SchedulingRepository.class);
        service = new SchedulingServiceImpl(transactionTypeContext, repository);
    }

    @Test
    void whenReceiveAValidSchedulingRequest_ShouldSaveScheduling() {
        SchedulingRequest request = buildRequest();
        Tax tax = buildTax();
        Scheduling scheduling = buildScheduling(request, tax);

        when(transactionTypeContext.getTransactionTypeStrategy(request))
                .thenReturn(transactionTypeStrategy);

        when(transactionTypeStrategy.execute(request)).thenReturn(tax);

        Assertions.assertDoesNotThrow(() -> { repository.save(scheduling); });

        SchedulingResponse response = service.saveScheduleTransfer(request);

        Assertions.assertEquals(request.getOriginAccount(), response.getOriginAccount());
        Assertions.assertEquals(request.getScheduleDate(), response.getScheduleDate());
        Assertions.assertEquals(request.getValue(), response.getValue());
        Assertions.assertEquals(request.getTransactionType(), response.getTransactionType());
        Assertions.assertEquals(tax.getTaxValue(), response.getTax());
    }

    @Test
    void whenReceiveAValidSchedulingRequestButCantSave_ShouldThrowsAException() {
        SchedulingRequest request = buildRequestInvalid();
        Tax tax = buildTax();
        Scheduling scheduling = buildScheduling(request, tax);

        when(transactionTypeContext.getTransactionTypeStrategy(any(SchedulingRequest.class)))
                .thenReturn(transactionTypeStrategy);

        when(transactionTypeStrategy.execute(any(SchedulingRequest.class))).thenReturn(tax);

        when(repository.save(scheduling)).thenThrow(TechnicalException.class);

        Assertions.assertThrows(TechnicalException.class, () -> {
            service.saveScheduleTransfer(request); });

    }

    private SchedulingRequest buildRequest() {
        SchedulingRequest request = new SchedulingRequest(
                "XXXXX",
                "XXXXXX",
                LocalDate.now(),
                BigDecimal.valueOf(150),
                "A");
    }

    private Tax buildTax() {
        Tax tax = new Tax();
        tax.setTaxValue(BigDecimal.valueOf(3.50));
        tax.setTransactionType("A");

        return tax;
    }

    private Scheduling buildScheduling(SchedulingRequest request, Tax tax) {
        Scheduling scheduling = new Scheduling();
        scheduling.setOriginAccount(request.getOriginAccount());
        scheduling.setDestinationAccount(request.getDestinationAccount());
        scheduling.setScheduleDate(request.getScheduleDate());
        scheduling.setTransferDate(LocalDate.now());
        scheduling.setTransferValue(request.getValue());
        scheduling.setTax(tax);

        return scheduling;
    }

    private SchedulingRequest buildRequestInvalid() {
        SchedulingRequest request = new SchedulingRequest(
                "XXXXX",
                "XXXXXX",
                LocalDate.now().minusDays(30),
                BigDecimal.valueOf(0),
                "A"
        );
    }
}
