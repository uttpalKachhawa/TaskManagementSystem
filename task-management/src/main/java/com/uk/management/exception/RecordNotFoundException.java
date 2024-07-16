package com.uk.management.exception;

import com.uk.management.error.ErrorCode;
import com.uk.management.error.TaskManagementErrorCode;
import com.uk.management.exception.base.BaseUncheckedException;

public abstract class RecordNotFoundException extends BaseUncheckedException {

    protected   RecordNotFoundException(String exceptionCode){
        super(TaskManagementErrorCode.RECORD_NOT_FOUND,exceptionCode);
    }
    protected RecordNotFoundException(ErrorCode errorCode, String exceptionCode) {
        super(TaskManagementErrorCode.RECORD_NOT_FOUND, exceptionCode);
    }

    protected RecordNotFoundException(String message, ErrorCode errorCode, String exceptionCode) {
        super(message,TaskManagementErrorCode.RECORD_NOT_FOUND , exceptionCode);
    }

    protected RecordNotFoundException(String message, Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(message, cause, TaskManagementErrorCode.RECORD_NOT_FOUND, exceptionCode);
    }

    protected RecordNotFoundException(Throwable cause, ErrorCode errorCode, String exceptionCode) {
        super(cause, TaskManagementErrorCode.RECORD_NOT_FOUND, exceptionCode);
    }
}
