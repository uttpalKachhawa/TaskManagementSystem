package com.uk.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ResponseTO<T> {

    private static final long serialVersionUID = 1L;

    public ResponseTO(T result) {
    }

  /* Set if error occur*/
    private boolean error;


    /*set the error codes and msg*/
    private List<Error> errors;

    /*  Set the response data*/
    private T data;


}
