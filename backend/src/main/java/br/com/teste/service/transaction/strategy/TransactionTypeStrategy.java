package br.com.teste.service.transaction.strategy;

import br.com.teste.api.dto.SchedulingRequest;
import br.com.teste.domain.Tax;

public interface TransactionTypeStrategy {

    Tax execute(SchedulingRequest schedulingRequest);

    String transactionType();
}
