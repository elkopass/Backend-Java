package ru.tsu.hits.dto;

import lombok.Data;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.UserEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class TaskDto {

    private  String id;

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
