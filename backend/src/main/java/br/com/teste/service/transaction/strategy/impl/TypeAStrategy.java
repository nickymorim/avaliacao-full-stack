package br.com.teste.service.transaction.strategy.impl;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.domain.Tax;
import br.com.teste.domain.enumeration.TransactionType;
import br.com.teste.service.transaction.strategy.TransactionTypeStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TypeAStrategy implements TransactionTypeStrategy {

    @Override
    public Tax execute(SchedulingRequest schedulingRequest) {
        BigDecimal value = schedulingRequest.getValue();

        final BigDecimal tax = value.multiply(BigDecimal.valueOf(0.03)).add(BigDecimal.valueOf(3.00));

        Tax taxFinal = new Tax();
        taxFinal.setTransactionType(transactionType());
        taxFinal.setTaxValue(tax.setScale(2, RoundingMode.HALF_EVEN));

        return taxFinal;
//        return Tax.builder()
//                .value(tax.setScale(2, RoundingMode.HALF_EVEN))
//                .transactionType(transactionType())
//                .build();
    }

    @Override
    public String transactionType() {
        return TransactionType.TRANSACTION_A.getTransactionType();
    }

}
