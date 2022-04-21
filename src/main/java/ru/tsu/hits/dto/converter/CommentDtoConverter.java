package ru.tsu.hits.dto.converter;

import ru.tsu.hits.dto.CRUCommentDto;
import ru.tsu.hits.dto.CommentDto;
import ru.tsu.hits.dto.TaskDto;
import ru.tsu.hits.entity.CommentEntity;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CommentDtoConverter {
    public static CommentEntity convertDtoToEntity(CRUCommentDto dto, UserEntity user, List<TaskEntity> task){
        CommentEntity CommentEntity  = new CommentEntity();

        CommentEntity.setUuid(UUID.randomUUID().toString());
        CommentEntity.setCreateDate(dto.getCreateDate());
        CommentEntity.setEditDate(dto.getEditDate());
        CommentEntity.setCommentText(dto.getCommentText());
        CommentEntity.setTask(task);
        CommentEntity.setUser(user);



        return  CommentEntity;
    }

    public  static CommentDto convertEntityToDto(CommentEntity commentEntity){

        CommentDto commentDto = new CommentDto();

        commentDto.setId(commentEntity.getUuid());
        commentDto.setCreateDate(commentEntity.getCreateDate());
        commentDto.setEditDate(commentEntity.getEditDate());
        commentDto.setCommentText(commentEntity.getCommentText());

        List<String> tmp = new ArrayList<>();
        for (TaskEntity elem: commentEntity.getTask()) {
            tmp.add(elem.getUuid());
        }
        commentDto.setTask(tmp);

        commentDto.setUser(commentEntity.getUser().getUuid());

        return commentDto;
    }

    private  static List<TaskDto> convertTaskToDto(List<TaskEntity> taskEntities){
        List<TaskDto> result = new ArrayList<>();

        taskEntities.forEach(element ->{
            TaskDto taskDto = new TaskDto();

            taskDto.setCreateDate(element.getCreateDate());
            taskDto.setEditDate(element.getEditDate());
            taskDto.setName(element.getName());
            taskDto.setDescription(element.getDescription());
            taskDto.setCreator(element.getCreator().getUuid());
            taskDto.setExecuter(element.getExecuter().getUuid());
            taskDto.setMark(element.getMark());
            taskDto.setPriority(element.getPriority());
            taskDto.setProject(element.getProjectId().getUuid());
            result.add(taskDto);
        });
        return result;
    }
}
