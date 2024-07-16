package com.uk.management.dto;

import com.uk.management.enums.TaskPriority;
import com.uk.management.enums.TaskStatus;
import com.uk.management.validations.PriorityTypeEnumValidation;
import com.uk.management.validations.StatusTypeEnumValidation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskRequestDTO {

    @NotNull(message="TM001")
    private String title;
    private String description;
    @NotNull(message="TM002")
    @PriorityTypeEnumValidation()
    private String priority;
    @NotNull(message = "TM003")
    private String dueDate;
    @NotNull(message="TM004")
    @StatusTypeEnumValidation()
    private String status;
}
