package br.com.teste.api.dto;

import br.com.teste.domain.enumeration.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class SchedulingResponse {

    private String originAccount;

    private String destinationAccount;

    private LocalDate transferDate;

    private LocalDate scheduleDate;

    private BigDecimal value;

    private BigDecimal tax;

    private String transactionType;

}
