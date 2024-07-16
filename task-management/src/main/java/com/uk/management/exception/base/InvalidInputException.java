package com.uk.management.exception.base;

import com.uk.management.error.ErrorCode;
import com.uk.management.error.TaskManagementErrorCode;

public abstract class InvalidInputException extends BaseUncheckedException {

    protected InvalidInputException( String exceptionCode) {
        super(TaskManagementErrorCode.INVALID_INPUT, exceptionCode);
    }

    protected InvalidInputException(ErrorCode errorCode, String exceptionCode) {
        super(TaskManagementErrorCode.INVALID_INPUT, exceptionCode);
    }

    protected InvalidInputException(String message, ErrorCode errorCode, String exceptionCode) {
        super(message, TaskManagementErrorCode.INVALID_INPUT, exceptionCode);
    }

    protected InvalidInputException(String message, Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(message, cause, TaskManagementErrorCode.INVALID_INPUT, exceptionCode);
    }

    protected InvalidInputException(Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(cause, TaskManagementErrorCode.INVALID_INPUT, exceptionCode);
    }
}
