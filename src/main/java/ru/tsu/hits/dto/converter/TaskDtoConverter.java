package ru.tsu.hits.dto.converter;

import ru.tsu.hits.dto.*;
import ru.tsu.hits.entity.CommentEntity;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.entity.UserEntity;
import ru.tsu.hits.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class TaskDtoConverter {
    public static TaskEntity convertDtoToEntity(CRUTaskDto dto,UserEntity creator, UserEntity executer, ProjectEntity project){
        TaskEntity TaskEntity  = new TaskEntity();
        UserRepository userRepository ;


        TaskEntity.setUuid(UUID.randomUUID().toString());
        TaskEntity.setCreateDate(dto.getCreateDate());
        TaskEntity.setEditDate(dto.getEditDate());
        TaskEntity.setName(dto.getName());
        TaskEntity.setDescription(dto.getDescription());
        TaskEntity.setCreator(creator);
        TaskEntity.setExecuter(executer);
        TaskEntity.setPriority(dto.getPriority());
        TaskEntity.setMark(dto.getMark());
        TaskEntity.setProjectId(project);


        return  TaskEntity;
    }

    public  static TaskDto convertEntityToDto(TaskEntity TaskEntity){

        TaskDto TaskDto = new TaskDto();

        TaskDto.setId(TaskEntity.getUuid());
        TaskDto.setCreateDate(TaskEntity.getCreateDate());
        TaskDto.setEditDate(TaskEntity.getEditDate());
        TaskDto.setName(TaskEntity.getName());
        TaskDto.setDescription(TaskEntity.getDescription());
        TaskDto.setCreator(TaskEntity.getCreator().getUuid());
        TaskDto.setExecuter(TaskEntity.getExecuter().getUuid());
        TaskDto.setMark(TaskEntity.getMark());
        TaskDto.setPriority(TaskEntity.getPriority());
        TaskDto.setProject(TaskEntity.getProjectId().getUuid());

        return TaskDto;
    }
    private  static List<CommentDto> convertCommentToDto(List<CommentEntity> CommentEntities){
        List<CommentDto> result = new ArrayList<>();

        CommentEntities.forEach(element ->{
            CommentDto commentDto = new CommentDto();

            commentDto.setCreateDate(element.getCreateDate());
            commentDto.setEditDate(element.getEditDate());
            commentDto.setCommentText(element.getCommentText());
            List<String> tmp = null;
            for (TaskEntity elem: element.getTask()) {
                tmp.add(elem.getUuid());
            }
            commentDto.setTask(tmp);
            commentDto.setUser(element.getUser().getUuid());
            result.add(commentDto);
        });
        return result;
    }
}
