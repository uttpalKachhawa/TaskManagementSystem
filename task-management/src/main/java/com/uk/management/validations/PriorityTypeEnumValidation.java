package com.uk.management.validations;

import com.uk.management.enums.TaskPriority;
import com.uk.management.enums.TaskStatus;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = PriorityTypeEnumValidator.class)
public @interface PriorityTypeEnumValidation {
        //TaskPriority[] anyOf();
        /*String message() default "must be any of {anyOf}";*/
        public String message() default "Invalid Status: must be LOW, MEDIUM or HIGH";
        Class<?>[] groups() default {};
        Class<? extends Payload>[] payload() default {};
    }

