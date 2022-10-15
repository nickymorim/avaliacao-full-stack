package br.com.teste.exception.service;

import br.com.teste.exception.impl.ApiException;
import br.com.teste.exception.model.ApiErrorResponse;
import br.com.teste.exception.model.ResponseError;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    public ApiException createResponseError(ResponseError errorType, ApiException apiException) {
        apiException.getApiErrorResponse().setCode(String.valueOf(errorType.getStatusCode()));
        apiException.getApiErrorResponse().setDescription(errorType.getDescription());
        apiException.getApiErrorResponse().setMessage(errorType.getDescription());

        return apiException;
    }

}
