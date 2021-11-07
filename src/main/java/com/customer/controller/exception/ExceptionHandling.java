package com.customer.controller.exception;

import com.customer.exceptions.UserUnprocessableException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ExceptionHandling extends ResponseEntityExceptionHandler {

	private static final String EXCEPTION = "Exception: {}";

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleAnyOtherException(
		HttpServletRequest request, Exception ex) {

		return createErrorResponseEntity(ex, request, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserUnprocessableException.class)
	public ResponseEntity<ApiError> handleUserUnprocessableException(
		HttpServletRequest request, Exception ex) {

		return createErrorResponseEntity(ex, request, HttpStatus.UNPROCESSABLE_ENTITY);
	}

	private ResponseEntity<ApiError> createErrorResponseEntity(Exception ex,
															   HttpServletRequest request, HttpStatus status) {
		ApiError apiError = createError(ex, request, status);
		String errorMessage = toLogError(apiError);
		log.info(EXCEPTION, errorMessage);
		return new ResponseEntity<>(apiError, status);
	}

	private String toLogError(ApiError apiError) {
		String logAsString = "";
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			logAsString = objectMapper.writeValueAsString(apiError);
		} catch (JsonProcessingException e) {
			log.warn(e.getMessage());
		}
		return logAsString;
	}

	private ApiError createError(
		Exception exception, HttpServletRequest httpServletRequest, HttpStatus errorCode) {
		return ApiError.builder()
			.status(errorCode.value())
			.error(errorCode.getReasonPhrase())
			.exception(exception.getClass().getCanonicalName())
			.message(exception.getLocalizedMessage())
			.path(httpServletRequest.getRequestURI())
			.build();
	}
}
