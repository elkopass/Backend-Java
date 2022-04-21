package ru.tsu.hits.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CRUProjectDto {
    private  String id;

    private Date createDate;

    private Date editDate;

    private String name;

    private  String description;
}
