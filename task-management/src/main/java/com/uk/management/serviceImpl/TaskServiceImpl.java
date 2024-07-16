package com.uk.management.serviceImpl;

import com.uk.management.constants.TaskManagementConstants;
import com.uk.management.dto.TaskRequestDTO;
import com.uk.management.dto.TaskResponseDTO;
import com.uk.management.enums.TaskPriority;
import com.uk.management.enums.TaskStatus;
import com.uk.management.exception.RecordNotFoundException;
import com.uk.management.mapper.TaskMapper;
import com.uk.management.model.Task;
import com.uk.management.repository.TaskRepository;
import com.uk.management.service.TaskService;
import com.uk.management.utils.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final TaskMapper taskMapper;

    @Override
    public TaskResponseDTO createTask(TaskRequestDTO taskDTO) {
        Task task = taskMapper.mapToTaskEntity(taskDTO);
        task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
        task.setPriority(TaskPriority.valueOf(taskDTO.getPriority()));
        task.setDueDate(CommonUtils.getDate(taskDTO.getDueDate()));
        taskRepository.save(task);
        return taskMapper.toTaskResponseDTO(task);
    }

    @Override
    public List<TaskResponseDTO> getAllTask(){
        List<Task> tasks = taskRepository.findAll();
        return taskMapper.toTaskResponseDTOList(tasks);
    }

    @Override
    public TaskResponseDTO getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(()-> new RecordNotFoundException(TaskManagementConstants.ID_NOT_FOUND) {
                });
        return taskMapper.toTaskResponseDTO(task);
    }

    @Override
    public String updateTask(Long taskId, TaskRequestDTO taskDTO) {
        Optional<Task> taskOptional = Optional.ofNullable(taskRepository.findById(taskId)
                .orElseThrow(() -> new RecordNotFoundException(TaskManagementConstants.ID_NOT_FOUND) {
                }));
        if (taskOptional.isPresent()) {
            Task task = taskOptional.get();
            task.setStatus(TaskStatus.valueOf(taskDTO.getStatus()));
            task.setPriority(TaskPriority.valueOf(taskDTO.getPriority()));
            task.setDueDate(CommonUtils.getDate(taskDTO.getDueDate()));
            taskRepository.save(task);
        }

        return TaskManagementConstants.UPDATED_SUCCESSFULLY;
    }

    @Override
    public String deleteTask(Long taskId) {
        Optional<Task> taskOptional = Optional.ofNullable(taskRepository.findById(taskId)
                .orElseThrow(() -> new RecordNotFoundException(TaskManagementConstants.ID_NOT_FOUND) {
                }));
        if(taskOptional.isPresent()) {
            taskRepository.deleteById(taskId);
        }
        return TaskManagementConstants.DELETED_SUCCESSFULLY;
    }

    @Override
    public List<TaskResponseDTO> getAllTasksByTaskStatus(String taskStatus)
    {
        List<Task> taskList= new ArrayList<>();
        TaskStatus[] values= TaskStatus.values();
        for(TaskStatus tasks: values){
            if(tasks.name().equals(taskStatus)){
                 taskList= taskRepository.findByStatus(tasks);
            }
        }

        return  taskMapper.toTaskResponseDTOList(taskList);
    }

    @Override
    public List<TaskResponseDTO> getAllTasksByPriority(String priority) {
        List<Task> taskList= new ArrayList<>();
        TaskPriority[] values= TaskPriority.values();
        for(TaskPriority taskPriority: values) {
            if (taskPriority.name().equals(taskPriority)) {
                taskList = taskRepository.findByPriority(taskPriority);
            }
        }
        return  taskMapper.toTaskResponseDTOList(taskList);
    }


}
