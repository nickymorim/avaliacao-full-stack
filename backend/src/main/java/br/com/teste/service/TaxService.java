package br.com.teste.service;

import br.com.teste.api.dto.SchedulingRequest;

public interface TaxService {

    String transactionType(SchedulingRequest schedulingRequest);

    long getDays(SchedulingRequest schedulingRequest);

}
