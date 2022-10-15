package br.com.teste.exception.model;

import lombok.Getter;

@Getter
public enum ResponseError {

    TECHNICAL_EXCEPTION(500, "Technical Exception"),
    BUSINESS_EXCEPTION(453, "Business Exception"),
    BAD_REQUEST_EXCEPTION(400, "Bad Request");

    private final Integer statusCode;
    private final String description;

    ResponseError(Integer statusCode, String description) {
        this.statusCode = statusCode;
        this.description = description;
    }
}