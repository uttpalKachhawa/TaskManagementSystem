package com.uk.management.service;


import com.uk.management.dto.TaskRequestDTO;
import com.uk.management.dto.TaskResponseDTO;

import java.util.List;

public interface TaskService {

    TaskResponseDTO createTask(TaskRequestDTO taskDTO);

    List<TaskResponseDTO> getAllTask();

    TaskResponseDTO getTaskById(Long taskId);

    String updateTask(Long taskId, TaskRequestDTO taskDTO);

    String deleteTask(Long taskId);

    List<TaskResponseDTO> getAllTasksByTaskStatus(String taskStatus);

    List<TaskResponseDTO> getAllTasksByPriority(String priority);

}
