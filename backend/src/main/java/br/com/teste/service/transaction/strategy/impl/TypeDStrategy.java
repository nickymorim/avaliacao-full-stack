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
public class TypeDStrategy implements TransactionTypeStrategy {

    private final TaxServiceImpl taxService;

    private final TypeAStrategy typeAStrategy;

    private final TypeBStrategy typeBStrategy;

    private final TypeCStrategy typeCStrategy;

    public TypeDStrategy(TaxServiceImpl taxService, TypeAStrategy typeAStrategy, TypeBStrategy typeBStrategy, TypeCStrategy typeCStrategy) {
        this.taxService = taxService;
        this.typeAStrategy = typeAStrategy;
        this.typeBStrategy = typeBStrategy;
        this.typeCStrategy = typeCStrategy;
    }

    @Override
    public Tax execute(SchedulingRequest schedulingRequest) {
        Tax value = getTaxTypeD(schedulingRequest);

        Tax taxFinal = new Tax();
        taxFinal.setTransactionType(transactionType());
        taxFinal.setTaxValue(value.getTaxValue().setScale(2, RoundingMode.HALF_EVEN));

        return taxFinal;

//        return Tax.builder()
//                .value(value.getValue().setScale(2, RoundingMode.HALF_EVEN))
//                .transactionType(transactionType())
//                .build();
    }

    @Override
    public String transactionType() {
        return TransactionType.TRANSACTION_D.getTransactionType();
    }

    private Tax getTaxTypeD(SchedulingRequest schedulingRequest) {
        BigDecimal value = schedulingRequest.getValue();

        if (value.compareTo(BigDecimal.valueOf(1001.00)) < 0) {
            return typeAStrategy.execute(schedulingRequest);
        } else if (value.compareTo(BigDecimal.valueOf(1001.00)) > 0 &&
                value.compareTo(BigDecimal.valueOf(2000.00)) < 0) {
            return typeBStrategy.execute(schedulingRequest);
        } else if (value.compareTo(BigDecimal.valueOf(2000.00)) > 0) {
            return typeCStrategy.execute(schedulingRequest);
        } else {
            throw new RuntimeException(); //toDo lançar exception
        }
    }

}
