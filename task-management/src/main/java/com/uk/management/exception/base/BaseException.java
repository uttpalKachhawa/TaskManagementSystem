package com.uk.management.exception.base;

import com.uk.management.error.ErrorCode;

public interface BaseException {


    ErrorCode errorCode();

    String exceptionCode();

    String fallBackMessage();
}
