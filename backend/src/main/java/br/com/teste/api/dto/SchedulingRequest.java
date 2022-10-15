package br.com.teste.api.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@ToString
public class SchedulingRequest {

    private final String originAccount;

    private final String destinationAccount;

    private final LocalDate scheduleDate;

    private final BigDecimal value;

    private final String transactionType;

    @JsonCreator
    public SchedulingRequest(String originAccount, String destinationAccount, LocalDate scheduleDate, BigDecimal value, String transactionType) {
        this.originAccount = originAccount;
        this.destinationAccount = destinationAccount;
        this.scheduleDate = scheduleDate;
        this.value = value;
        this.transactionType = transactionType;
    }
}
