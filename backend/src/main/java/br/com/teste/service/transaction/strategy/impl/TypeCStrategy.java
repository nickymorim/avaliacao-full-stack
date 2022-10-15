package br.com.teste.service.transaction.strategy.impl;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.domain.Tax;
import br.com.teste.domain.enumeration.TransactionType;
import br.com.teste.service.impl.TaxServiceImpl;
import br.com.teste.service.transaction.strategy.TransactionTypeStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class TypeCStrategy implements TransactionTypeStrategy {

    private final TaxServiceImpl taxService;

    public TypeCStrategy(TaxServiceImpl taxService) {
        this.taxService = taxService;
    }

    @Override
    public Tax execute(SchedulingRequest schedulingRequest) {
        final BigDecimal tax = getTaxTypeC(schedulingRequest);

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
        return TransactionType.TRANSACTION_C.getTransactionType();
    }

    private BigDecimal getTaxTypeC(SchedulingRequest schedulingRequest) {
        long days = taxService.getDays(schedulingRequest);

        BigDecimal value = schedulingRequest.getValue();

        if(days > 10 && days <= 20) {
            return value.multiply(BigDecimal.valueOf(0.082));
        }

        if(days > 20 && days <= 30) {
            return value.multiply(BigDecimal.valueOf(0.069));
        }

        if(days > 30 && days <= 40)  {
            return value.multiply(BigDecimal.valueOf(0.047));
        } else {
            return value.multiply(BigDecimal.valueOf(0.017));
        }
    }

}
