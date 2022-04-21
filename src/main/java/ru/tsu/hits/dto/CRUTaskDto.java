package ru.tsu.hits.dto;

import lombok.Data;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.UserEntity;

import java.util.Date;

@Data
public class CRUTaskDto {

    private Date createDate;

    private Date editDate;

    private String name;

    private  String description;

    private String creator;

    private  String executer;

    private  String priority;

    private String Project;

    private  String mark;
}
