package ru.tsu.hits.dto.converter;

import ru.tsu.hits.dto.CRUProjectDto;
import ru.tsu.hits.dto.ProjectDto;
import ru.tsu.hits.dto.TaskDto;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.TaskEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ProjectDtoConverter {

    public static ProjectEntity convertDtoToEntity(CRUProjectDto dto){
        ProjectEntity projectEntity  = new ProjectEntity();

        projectEntity.setUuid(UUID.randomUUID().toString());
        projectEntity.setCreateDate(dto.getCreateDate());
        projectEntity.setEditDate(dto.getEditDate());
        projectEntity.setName(dto.getName());
        projectEntity.setDescription(dto.getDescription());

        return  projectEntity;
    }

    public  static ProjectDto convertEntityToDto(ProjectEntity projectEntity){

        ProjectDto projectDto = new ProjectDto();

        projectDto.setId(projectEntity.getUuid());
        projectDto.setCreateDate(projectEntity.getCreateDate());
        projectDto.setEditDate(projectEntity.getEditDate());
        projectDto.setName(projectEntity.getName());
        projectDto.setDescription(projectEntity.getDescription());

        return projectDto;
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
