package com.uk.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Error implements Serializable {
    private static final long serialVersionUID = 1L;
    private String errorCode;

    private String errorMessage;

}
