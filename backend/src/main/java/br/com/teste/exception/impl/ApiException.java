package br.com.teste.exception.impl;

import br.com.teste.exception.model.ApiErrorResponse;
import br.com.teste.exception.model.ApiMessage;
import br.com.teste.exception.model.ResponseError;
import lombok.*;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ApiException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -1575611004200538624L;

    private ApiErrorResponse apiErrorResponse = new ApiErrorResponse();

    public ApiException(Integer code, Exception ex) {
        ApiMessage error = ApiMessage.builder()
                .code(code)
                .build();
        populateErrorsResponse(Collections.singletonList(error));
    }

    public ApiException(String message, Exception ex) {
        ApiMessage error = ApiMessage.builder()
                .message(message)
                .build();
        populateErrorsResponse(Collections.singletonList(error));
    }

    public ApiException(Integer code, String message, Exception ex, String... parameters) {
        ApiMessage error = ApiMessage.builder()
                .code(code)
                .message(message)
                .parameters(parameters)
                .build();
        populateErrorsResponse(Collections.singletonList(error));
    }


    public ApiException(Integer code, String message, Object... parameters) {

        ApiMessage build = ApiMessage.builder()
                .code(code)
                .message(message)
                .parameters(parameters)
                .build();

        populateErrorsResponse(Collections.singletonList(build));
    }


    public ApiException(Integer code, String message) {
        ApiMessage build = ApiMessage.builder()
                .code(code)
                .message(message)
                .build();
        populateErrorsResponse(Collections.singletonList(build));

    }

    public ApiException(String message, ResponseError responseError) {
        ApiMessage error = ApiMessage.builder()
                .code(responseError.getStatusCode())
                .message(message)
                .build();
        populateErrorsResponse(Collections.singletonList(error));
    }

    public void populateErrorsResponse(List<ApiMessage> errors) {
        this.apiErrorResponse = ApiErrorResponse
                .builder()
                .errors(errors)
                .build();
    }

    public ApiErrorResponse getApiErrorResponse() {
        return apiErrorResponse;
    }

}