package com.uk.management.exception.base;

public abstract class BaseCheckedException extends  Exception {
    protected BaseCheckedException(String exceptionCode, Throwable cause) {
        super(exceptionCode, cause);
    }

    protected BaseCheckedException(String exceptionCode) {
        super(exceptionCode);
    }


    public String exceptionCode(){
        return this.getMessage();
    }
}
