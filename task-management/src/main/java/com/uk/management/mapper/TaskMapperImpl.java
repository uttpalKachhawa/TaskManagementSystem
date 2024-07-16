package com.uk.management.mapper;

import com.uk.management.dto.TaskRequestDTO;
import com.uk.management.dto.TaskResponseDTO;
import com.uk.management.model.Task;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskMapperImpl implements TaskMapper {
    @Override
    public Task mapToTaskEntity(TaskRequestDTO taskRequestDTO) {
        if (taskRequestDTO == null) {
            return null;
        }
        Task task = new Task();
        task.setTitle(taskRequestDTO.getTitle());
        task.setDescription(taskRequestDTO.getDescription());
        return task;
    }

    @Override
    public List<TaskResponseDTO> toTaskResponseDTOList(List<Task> tasks) {
        if (tasks == null) {
            return null;
        }
        List<TaskResponseDTO> taskResponseDTOList = new ArrayList<>(tasks.size());
        tasks.forEach(task -> {
            taskResponseDTOList.add(taskEntityToTaskResponseDTO(task));
        });

        return taskResponseDTOList;
    }

    @Override
    public TaskResponseDTO toTaskResponseDTO(Task task) {
        if (task == null) {
            return null;
        }
        return taskEntityToTaskResponseDTO(task);
    }

    protected TaskResponseDTO taskEntityToTaskResponseDTO(Task task) {
        TaskResponseDTO taskResponseDTO = new TaskResponseDTO();
        taskResponseDTO.setTaskId(task.getId());
        taskResponseDTO.setTitle(task.getTitle());
        taskResponseDTO.setDescription(task.getDescription());
        taskResponseDTO.setDueDate(task.getDueDate().toString());
        taskResponseDTO.setStatus(task.getStatus().toString());
        taskResponseDTO.setPriority(task.getPriority().toString());
        return taskResponseDTO;
    }


}
