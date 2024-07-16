package com.uk.management.error;

import org.springframework.http.HttpStatus;

public enum TaskManagementErrorCode implements ErrorCode {


    INVALID_INPUT("INVALID_INPUT", HttpStatus.BAD_REQUEST),
    DUPLICATE_RECORD("DUPLICATE_RECORD", HttpStatus.CONFLICT),
    SERVER_CONFLICT("SERVER_CONFLICT", HttpStatus.CONFLICT),
    RECORD_NOT_FOUND("RECORD_NOT_FOUND", HttpStatus.OK),
    UNAUTHORIZED("UNAUTHORIZED_ACCESS", HttpStatus.FORBIDDEN),
    SERVER_ERROR("SERVER_ERROR", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String errorCode;

    private final HttpStatus httpStatus;

    TaskManagementErrorCode(String errorCode, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    @Override
    public String errorCode() {
        return this.errorCode;
    }

    @Override
    public HttpStatus httpStatus() {
        return this.httpStatus;
    }
}
