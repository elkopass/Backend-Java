package ru.tsu.hits.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
public class ProjectDto {

    private  String id;

    private Date createDate;

    private Date editDate;

    private String name;

    private  String description;
}
