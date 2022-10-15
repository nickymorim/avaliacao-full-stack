package br.com.teste.exception.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ApiMessage implements Serializable {

    private static final long serialVersionUID = 7242344463403853932L;

    @JsonProperty("code")
    private Integer code;

    @JsonProperty("message")
    private String message;

    @JsonIgnore
    private transient Object[] parameters;

}