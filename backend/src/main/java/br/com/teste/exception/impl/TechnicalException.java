package br.com.teste.exception.impl;

import java.io.Serializable;

import static br.com.teste.exception.model.ResponseError.TECHNICAL_EXCEPTION;

public class TechnicalException extends ApiException implements Serializable {

    private static final long serialVersionUID = -236177636748934472L;

    public TechnicalException(String message, Exception ex) {
        super(message, ex);
    }

    public TechnicalException(String message) {
        super(message, TECHNICAL_EXCEPTION);
    }

}