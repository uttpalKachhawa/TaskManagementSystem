package com.uk.management.repository;

import com.uk.management.enums.TaskPriority;
import com.uk.management.enums.TaskStatus;
import com.uk.management.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {

      List<Task> findByStatus(TaskStatus taskStatusEnum);

      List<Task> findByPriority(TaskPriority taskPriorityEnum);

}
