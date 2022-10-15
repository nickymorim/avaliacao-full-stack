package br.com.teste.exception.advice;

import br.com.teste.exception.impl.ApiException;
import br.com.teste.exception.impl.BusinessException;
import br.com.teste.exception.impl.TechnicalException;
import br.com.teste.exception.model.ApiErrorResponse;
import br.com.teste.exception.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static br.com.teste.exception.model.ResponseError.*;

@Slf4j
@RestControllerAdvice
public class DefaultControllerAdvice {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultControllerAdvice.class);

    private final MessageService messageService;

    public DefaultControllerAdvice(MessageService messageService) {
        this.messageService = messageService;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorResponse> handleBusinessException(BusinessException apiException) {
        final ApiException responseError = messageService.createResponseError(BUSINESS_EXCEPTION, apiException);

        return ResponseEntity
                .status(BUSINESS_EXCEPTION.getStatusCode())
                .body(responseError.getApiErrorResponse());
    }

    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<ApiErrorResponse> handleTechnicalException(TechnicalException apiException) {
        final ApiException responseError = messageService.createResponseError(TECHNICAL_EXCEPTION, apiException);

        return ResponseEntity
                .status(TECHNICAL_EXCEPTION.getStatusCode())
                .body(responseError.getApiErrorResponse());
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public ApiErrorResponse onConstraintValidationException(ConstraintViolationException constraintViolations) {

        return ApiErrorResponse.builder()
                .code(String.valueOf(BAD_REQUEST_EXCEPTION.getStatusCode()))
                .description(BAD_REQUEST_EXCEPTION.getDescription())
                .message(BAD_REQUEST_EXCEPTION.getDescription())
                .build();

    }

}