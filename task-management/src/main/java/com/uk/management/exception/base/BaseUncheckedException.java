package com.uk.management.exception.base;

import com.uk.management.error.ErrorCode;

public class BaseUncheckedException extends RuntimeException implements BaseException{

    private static final long serialVersionUID = 3627128398757176821L;


    protected final ErrorCode errorCode;
    protected final String exceptionCode;
    protected String fallBackMessage;

    public BaseUncheckedException(ErrorCode errorCode, String exceptionCode) {
        this.errorCode = errorCode;
        this.exceptionCode = exceptionCode;
    }

    public BaseUncheckedException(String message, ErrorCode errorCode, String exceptionCode) {
        super(message);
        this.errorCode = errorCode;
        this.exceptionCode = exceptionCode;
    }

    public BaseUncheckedException(String message, Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(message, cause);
        this.errorCode = errorCode;
        this.exceptionCode = exceptionCode;
    }

    public BaseUncheckedException(Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(cause);
        this.errorCode = errorCode;
        this.exceptionCode = exceptionCode;
    }
    


    @Override
    public String exceptionCode() {
        return this.exceptionCode;
    }

    @Override
    public ErrorCode errorCode() {
        return this.errorCode;
    }

    @Override
    public String fallBackMessage() {
        return this.fallBackMessage;
    }
}
