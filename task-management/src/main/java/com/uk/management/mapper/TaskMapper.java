package com.uk.management.mapper;


import com.uk.management.dto.TaskRequestDTO;
import com.uk.management.dto.TaskResponseDTO;
import com.uk.management.model.Task;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    Task mapToTaskEntity(TaskRequestDTO taskRequestDTO);
    List<TaskResponseDTO> toTaskResponseDTOList(List<Task> tasks);
    TaskResponseDTO toTaskResponseDTO(Task task);
}
