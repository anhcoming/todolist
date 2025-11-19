package com.example.todolist.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class TaskDto {
    private Integer id;

    private String title;

    private String content;

    private Date createdDate;

    private Date endDate;

    private Integer createdBy;

    private Integer status;

    private Integer priority;

}
