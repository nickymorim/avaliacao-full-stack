package br.com.teste.exception.impl;

import java.io.Serializable;

public class BusinessException extends ApiException implements Serializable {

    private static final long serialVersionUID = 4367259082231954578L;

    public BusinessException(String message, Exception ex) {
        super(message, ex);
    }

}