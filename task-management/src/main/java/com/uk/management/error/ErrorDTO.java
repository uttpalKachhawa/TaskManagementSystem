package com.uk.management.error;

import lombok.Data;

@Data
public class ErrorDTO {

    private String taskManagementErrorCode;
    private String serviceErrorCode;
    private String errorMessage;

    public ErrorDTO(String taskManagementErrorCode, String serviceErrorCode, String errorMessage) {
        this.taskManagementErrorCode = taskManagementErrorCode;
        this.serviceErrorCode = serviceErrorCode;
        this.errorMessage = errorMessage;
    }
}
