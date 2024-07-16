package com.uk.management.validations;

import com.uk.management.enums.TaskStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, ANNOTATION_TYPE, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = com.uk.management.validations.StatusTypeEnumValidator.class)
public @interface StatusTypeEnumValidation {
    //Error Message
       // TaskStatus[] anyOf();
    public String message() default "Invalid Status: must be PENDING, ASSIGNED or DONE";
        //String message() default "must be any of {anyOf}";
        //represents group of constraints
        Class<?>[] groups() default {};
    //represents additional information about annotation
        Class<? extends Payload>[] payload() default {};
    }

