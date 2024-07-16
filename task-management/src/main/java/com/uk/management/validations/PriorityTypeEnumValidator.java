package com.uk.management.validations;

import com.uk.management.enums.TaskPriority;
import com.uk.management.enums.TaskStatus;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;

public class PriorityTypeEnumValidator implements ConstraintValidator<PriorityTypeEnumValidation, String> {

  /*  private TaskPriority[] taskPriorities;*/

   /* @Override
    public void initialize(PriorityTypeEnumValidation priorityTypeEnumValidation) {
        this.taskPriorities = priorityTypeEnumValidation.anyOf();
    }*/

    @Override
    public boolean isValid(String priority, ConstraintValidatorContext cxt) {
        List list = Arrays.asList(new String[]{"LOW","MEDIUM","HIGH"});
        return list.contains(priority);
    }

   /* @PriorityTypeEnumValidation(anyOf = {TaskPriority.HIGH, TaskPriority.MEDIUM, TaskPriority.LOW})
    private TaskPriority taskPriority;*/

}
