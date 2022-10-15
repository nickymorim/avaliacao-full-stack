package br.com.teste.service.transaction;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.exception.impl.BadRequestException;
import br.com.teste.service.TaxService;
import br.com.teste.service.transaction.strategy.TransactionTypeStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
public class TransactionTypeContext {

    private final List<TransactionTypeStrategy> strategies;

    private final TaxService taxService;

    public TransactionTypeStrategy getTransactionTypeStrategy(SchedulingRequest schedulingRequest) {
        String transactionType = taxService.transactionType(schedulingRequest);

        return strategies.stream()
                .filter(applyFilter(transactionType))
                .findFirst()
                .orElseThrow(() -> new BadRequestException("Error com o tipo de transação selecionada." +
                        " Por favor, tente novamente."));
    }

    private Predicate<TransactionTypeStrategy> applyFilter(String transactionType) {
        return tax -> tax
                .transactionType()
                .equals(transactionType);
    }
}
