package com.uk.management.exception;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uk.management.dto.Error;
import com.uk.management.dto.ResponseTO;
import com.uk.management.exception.base.BaseUncheckedException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


import javax.validation.ConstraintViolationException;
import java.util.*;
import java.util.stream.Collectors;


@RestControllerAdvice
@Slf4j
public class TaskManagementExceptionHandler {
	private static final String INTERNAL_SERVER_ERROR_MSG = "Something went wrong";
	private static final String MALFORMED_JSON_ERROR = "malformed json. check if all fields are correct";
	private static final String MISSING_HEADER_ERROR_MSG = "required header `%s` is missing";
	private static final String METHOD_ARGUMENT_TYPE_MISMATCH_MSG = "parameter `%s` with value = `%s` is invalid";

	@Autowired
	private MessageSource messageSource;

	/**
	 * handle json mapping errors
	 *
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(JsonMappingException.class)
	public ResponseEntity<ResponseTO<Error>> handleConverterErrors(JsonMappingException exception) {
		log.error(exception.getMessage(), exception);
		String fieldName = exception.getPath().get(0).getFieldName();
		return new ResponseEntity<>(getCustomError("JSON.MAPPING.EXCEPTION", String.format("Wrong value format for field '%s'.", fieldName)), HttpStatus.BAD_REQUEST);
	}

	/**
	 * handle baseUncheckedException
	 *
	 * @param baseUncheckedException
	 * @return
	 */
	@ExceptionHandler(BaseUncheckedException.class)
	public ResponseEntity<ResponseTO<Error>> handleBaseException(BaseUncheckedException baseUncheckedException) {
		logException(baseUncheckedException);
		return new ResponseEntity<>(getErrorDTO(baseUncheckedException), baseUncheckedException.errorCode().httpStatus());
	}

	/**
	 * handle invalid method arguments exception
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler({MethodArgumentNotValidException.class})
	public ResponseEntity<ResponseTO<Error>> invalidParamsExceptionHandler(MethodArgumentNotValidException e) {
		ResponseTO<Error> response = new ResponseTO<>();
		response.setError(Boolean.TRUE);
		List<Error> errorList = resolveBindingResultErrors(e.getBindingResult());
		response.setErrors(errorList);
		log.warn("MethodArgumentNotValidException occur in system due to ", e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({HttpMessageNotReadableException.class})
	public ResponseEntity<ResponseTO<Error>> httpMessageNotReadableExceptionHandler(HttpMessageNotReadableException e) {
		ResponseTO<Error> response = new ResponseTO<>();
		response.setError(Boolean.TRUE);
		List<Error> errorList = List.of(new Error(HttpStatus.BAD_REQUEST.toString(), MALFORMED_JSON_ERROR));
		response.setErrors(errorList);
		log.warn("HttpMessageNotReadableException occurred. StackTrace:", e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}


	@ExceptionHandler({HttpClientErrorException.class})
	public ResponseEntity<ResponseTO<Error>> httpClientErrorExceptionHandler(HttpClientErrorException e) {
		ResponseTO<Error> response = new ResponseTO<>();
		response.setError(Boolean.TRUE);
		ObjectMapper objectMapper = new ObjectMapper();
		ResponseTO responseTemp = null;
		try {
			responseTemp = objectMapper.readValue(parseResponse(e.getMessage()), ResponseTO.class);
		} catch (JsonProcessingException ex) {
			log.warn("JsonProcessingException occurred inside HttpClientErrorExceptionHandler due to ",ex);
		}
		List<Error> errors = responseTemp.getErrors();
		response.setErrors(errors);
		log.warn("HttpClientErrorException occur in system due to ", e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	private String parseResponse(String data) {
		String d = Objects.requireNonNull(data).split(": ")[1];
		String jsonData = d.substring(1, d.length() - 1);
		return jsonData;
	}

	/**
	 * called when api call has some missing parameter
	 * @param e
	 * @return ResponseEntity<ResponseTO<Error>>
	 */
	@ExceptionHandler({MissingServletRequestParameterException.class})
	public ResponseEntity<ResponseTO<Error>> missingServletRequestParameterExceptionHandler(MissingServletRequestParameterException e) {
		ResponseTO<Error> response = new ResponseTO<>();
		response.setError(Boolean.TRUE);
		List<Error> errorList = List.of(new Error("MISSING.PARAMETER", e.getMessage()));
		response.setErrors(errorList);
		log.warn("MissingServletRequestParameterException occurred. StackTrace:", e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({HttpRequestMethodNotSupportedException.class})
	public ResponseEntity<ResponseTO<Error>> httpRequestMethodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException e) {
		ResponseTO<Error> response = new ResponseTO<>();
		response.setError(Boolean.TRUE);
		List<Error> errorList = List.of(new Error("REQUEST.METHOD.INVALID", e.getMessage()));
		response.setErrors(errorList);
		log.warn("HttpRequestMethodNotSupportedException occurred. StackTrace:", e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ConstraintViolationException.class})
	public ResponseEntity<ResponseTO<Error>> constraintViolationExceptionHandler(ConstraintViolationException e) {
		ResponseTO<Error> response = new ResponseTO<>();
		response.setError(Boolean.TRUE);
		List<Error> errorList = e.getConstraintViolations().stream()
				.map(cv -> new Error(cv.getMessageTemplate(), getMessageFromMessageSource(cv.getMessageTemplate())))
				.collect(Collectors.toList());
		response.setErrors(errorList);
		log.warn("ConstraintViolationException occurred. StackTrace:", e);
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
	}

	/**
	 * handle all kind of exceptions which is not handled in system
	 *
	 * @param exception
	 * @return
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseTO<Error>> handleException(Exception exception) {
		log.error("handleException ", exception);
		ResponseTO<Error> responseTO = new ResponseTO<>();
		responseTO.setError(Boolean.TRUE);
		responseTO.setErrors(List.of(new Error(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), INTERNAL_SERVER_ERROR_MSG)));
		return new ResponseEntity<>(responseTO, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	public ResponseEntity<ResponseTO<Error>> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
		log.warn("MissingRequestHeaderException occurred. StackTrace:", e);
		ResponseTO<Error> responseTO = new ResponseTO<>();
		responseTO.setError(Boolean.TRUE);
		responseTO.setErrors(List.of(new Error("MISSING.REQUIRED.HEADER", String.format(MISSING_HEADER_ERROR_MSG, e.getHeaderName()))));
		return new ResponseEntity<>(responseTO, HttpStatus.BAD_REQUEST);
	}

	/**
	 * This handler is called when someone provides an invalid value for a api's params
	 */
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseTO<Error>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		log.warn("IllegalArgumentException occurred. StackTrace:", e);
		ResponseTO<Error> responseTO = new ResponseTO<>();
		responseTO.setError(Boolean.TRUE);
		String errorMsg = String.format(METHOD_ARGUMENT_TYPE_MISMATCH_MSG, e.getName(), e.getValue());
		responseTO.setErrors(List.of(new Error("INVALID.PARAMETER.VALUE", errorMsg)));
		return new ResponseEntity<>(responseTO, HttpStatus.BAD_REQUEST);
	}


	/**
	 * Right now default locale is used. This can be changed if we have locale information associated with user or is coming in user request.
	 *
	 * @param baseUncheckedException baseException
	 * @return Generic Response
	 */
	private ResponseTO<Error> getErrorDTO(BaseUncheckedException baseUncheckedException) {
		String fallbackMessage = (null == baseUncheckedException.fallBackMessage()) ? baseUncheckedException.exceptionCode() : baseUncheckedException.fallBackMessage();
		String effectiveMessage = messageSource.getMessage(baseUncheckedException.exceptionCode(), null, fallbackMessage, Locale.getDefault());

		ResponseTO<Error> responseTO = new ResponseTO<>();
		responseTO.setError(true);
		responseTO.setErrors(List.of(new Error(baseUncheckedException.exceptionCode(), effectiveMessage)));

		return responseTO;
	}

	private ResponseTO<Error> getCustomError(String errorCode, String errorMsg) {
		ResponseTO<Error> responseTO = new ResponseTO<>();
		responseTO.setError(true);
		responseTO.setErrors(Arrays.asList(new Error(errorCode, errorMsg)));
		return responseTO;
	}

	private void logException(BaseUncheckedException baseUncheckedException) {
		log.error(baseUncheckedException.exceptionCode(), baseUncheckedException);
	}

	private List<Error> resolveBindingResultErrors(BindingResult bindingResult) {
		return bindingResult.getFieldErrors().stream()
				.map(fieldError -> new Error(fieldError.getDefaultMessage(), messageSource.getMessage(Objects.requireNonNull(fieldError.getDefaultMessage()), null, Locale.getDefault())))
				.sorted(Comparator.comparing(Error::getErrorCode)).distinct()
				.collect(Collectors.toList());
	}

	private String getMessageFromMessageSource(String message) {
		return messageSource.getMessage(message, null, message, Locale.getDefault());
	}
}
