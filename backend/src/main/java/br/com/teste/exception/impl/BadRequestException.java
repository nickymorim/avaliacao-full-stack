package br.com.teste.exception.impl;

import br.com.teste.exception.model.ResponseError;

import java.io.Serializable;

public class BadRequestException extends ApiException implements Serializable {

    private static final long serialVersionUID = 4367259082231954578L;

    public BadRequestException(String message, Exception ex) {
        super(message, ex);
    }

    public BadRequestException(String message) {
        super(message, ResponseError.BAD_REQUEST_EXCEPTION);
    }
}