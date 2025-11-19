package com.example.todolist.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "task", schema = "todolist")
public class TaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "content", length = 1000)
    private String content;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "status")
    private Integer status;

    @Column(name = "priority")
    private Integer priority;

}