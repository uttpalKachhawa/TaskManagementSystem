package com.uk.management.validations;

import com.uk.management.enums.TaskStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class StatusTypeEnumValidator implements ConstraintValidator<StatusTypeEnumValidation, String> {



    public boolean isValid(String status, ConstraintValidatorContext cxt) {
        List list = Arrays.asList(new String[]{"ASSIGNED","PENDING","DONE"});
        return list.contains(status);
    }


   // private TaskStatus[] taskStatuses;

    /*@Override
    public void initialize(StatusTypeEnumValidation statusTypeEnumValidation) {
        this.taskStatuses = statusTypeEnumValidation.();
    }*/

   /* @Override
    public boolean isValid(TaskStatus taskStatus, ConstraintValidatorContext constraintValidatorContext) {
        return taskStatus==null || Arrays.asList(taskStatuses).contains(taskStatus);
    }*/



   /* @StatusTypeEnumValidation(anyOf = {TaskStatus.ASSIGNED, TaskStatus.PENDING, TaskStatus.DONE})
    private TaskStatus taskStatus;*/

}
