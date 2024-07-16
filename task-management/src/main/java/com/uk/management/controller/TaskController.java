package com.uk.management.controller;

import com.uk.management.dto.ResponseTO;
import com.uk.management.dto.TaskRequestDTO;
import com.uk.management.dto.TaskResponseDTO;
import com.uk.management.service.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/*
 * @author uttpalkachhawa on July, 2024
 */


@RestController
@Api(tags = { "TaskController" })
@RequestMapping("api/tasks")
@RequiredArgsConstructor
public class TaskController {

   private final TaskService taskService;


    @PostMapping("/task")
    @ApiOperation(value = "Post Method to add task")
    public ResponseEntity<ResponseTO<TaskResponseDTO>> createTask(
            @Valid @RequestBody TaskRequestDTO taskRequestDTO) {
        ResponseTO<TaskResponseDTO> response= new ResponseTO<>();
        response.setData(taskService.createTask(taskRequestDTO));
        return ResponseEntity.ok(response);

    }

    @GetMapping("/task")
    @ApiOperation(value = "Get Method to retrieve list of all tasks")
    public ResponseEntity<ResponseTO<List<TaskResponseDTO>>> getAllTasks() {
        ResponseTO<List<TaskResponseDTO>> response= new ResponseTO<>();
        response.setData(taskService.getAllTask());
        return ResponseEntity.ok(response);

    }

    @GetMapping("/task/{taskId}")
    @ApiOperation(value = "Get method to retrieve  task based on Id")
    public ResponseEntity<ResponseTO<TaskResponseDTO>> getTaskOnId(@PathVariable(value = "taskId") Long taskId) {
        ResponseTO<TaskResponseDTO> response= new ResponseTO<>();
        response.setData(taskService.getTaskById(taskId));
        return ResponseEntity.ok(response);

    }



    @GetMapping("/task/status/{status}")
    @ApiOperation(value = "Get method to retrieve list of Tasks on status")
    public ResponseEntity<ResponseTO<List<TaskResponseDTO>>> getAllTasksByTaskStatus(@PathVariable(name = "status") String status) {
        ResponseTO<List<TaskResponseDTO>> response= new ResponseTO<>();
        response.setData(taskService.getAllTasksByTaskStatus(status));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/task/priority/{priority}")
    @ApiOperation(value = "Get method to retrieve list of Tasks on priority")
    public ResponseEntity<ResponseTO<List<TaskResponseDTO>>> getAllTasksByPriority(@PathVariable(name = "priority") String priority) {
        ResponseTO<List<TaskResponseDTO>> response= new ResponseTO<>();
        response.setData(taskService.getAllTasksByPriority(priority));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/task/{taskId}")
    @ApiOperation(value = "Put Method to update Tasks on TaskId")
    public ResponseEntity<ResponseTO<String>> updateTask(@PathVariable Long taskId, @Valid @RequestBody TaskRequestDTO taskRequestDTO ) {
        ResponseTO<String> response= new ResponseTO<>();
        response.setData(taskService.updateTask(taskId,taskRequestDTO));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/task/{taskId}")
    @ApiOperation(value = "Delete Method to remove task based on Id")
    public ResponseEntity<ResponseTO<String>> deleteTask(@PathVariable Long taskId) {
        ResponseTO<String> response= new ResponseTO<>();
        response.setData(taskService.deleteTask(taskId));
        return ResponseEntity.ok(response);

    }


}
