package com.uk.management.dto;

import lombok.Data;

@Data
public class TaskResponseDTO {

    private Long taskId;
    private String title;
    private String description;
    private String priority;
    private String dueDate;
    private String status;
}
