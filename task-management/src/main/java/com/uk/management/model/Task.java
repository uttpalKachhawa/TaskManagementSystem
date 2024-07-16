package com.uk.management.model;


import com.uk.management.enums.TaskPriority;
import com.uk.management.enums.TaskStatus;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Task implements Serializable {
    private static final long serialVersionUID = 7080075161005200218L;

    @Id
    @Column(name="Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name="title", nullable = false)
    private String title;
    @Column(name="priority", nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskPriority priority;
    @Column(name="dueDate", nullable = false)
    private Date dueDate;
    @Column(name="description")
    private String description;
    @Column(name="status",nullable = false)
    @Enumerated(EnumType.STRING)
    private TaskStatus status;

}
