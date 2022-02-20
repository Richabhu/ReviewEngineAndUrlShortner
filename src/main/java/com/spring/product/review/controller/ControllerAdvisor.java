package com.spring.product.review.controller;

import com.spring.product.review.exceptions.CatalogProductNotFoundException;
import com.spring.product.review.responses.common.StatusCode;
import com.spring.product.review.responses.common.StatusResponse;
import com.spring.product.review.responses.common.enumeration.ErrorCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;

@ControllerAdvice
@Slf4j
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler({CatalogProductNotFoundException.class})
    protected ResponseEntity<Object> handleFeatureMappingNotFoundException(CatalogProductNotFoundException ex, WebRequest request) {
        log.error("Item  not found ", ex);
        return this.handleExceptionInternal(
                ex,
                new StatusResponse(ErrorCodes.ITEM_NOT_FOUND, 0),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler({ConstraintViolationException.class})
    protected ResponseEntity<Object> handleCustomRuntimeException(RuntimeException ex, WebRequest request) {
        log.error("Error : ", ex);
        return this.handleExceptionInternal(
                ex,
                new StatusResponse(ErrorCodes.REVIEW_SCORE_VALIDATION, 0),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }
}

