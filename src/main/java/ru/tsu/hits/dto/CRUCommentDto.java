package ru.tsu.hits.dto;

import lombok.Data;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.entity.UserEntity;

import java.util.Date;
import java.util.List;

@Data
public class CRUCommentDto {

    private Date createDate;

    private Date editDate;

    private String user;

    private List<String> task;

    private  String commentText;
}
