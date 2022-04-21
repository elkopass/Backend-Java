package ru.tsu.hits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.dto.CRUProjectDto;
import ru.tsu.hits.dto.ProjectDto;
import ru.tsu.hits.dto.converter.ProjectDtoConverter;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.exception.NotFoundException;
import ru.tsu.hits.repository.ProjectRepository;
import ru.tsu.hits.repository.TaskRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private  final ProjectRepository projectRepository;

    private  final TaskRepository taskRepository;

    @Transactional
    public ProjectDto createProject(CRUProjectDto dto){
        ProjectEntity projectEntity = ProjectDtoConverter.convertDtoToEntity(dto);

        projectEntity = projectRepository.save(projectEntity);

        return ProjectDtoConverter.convertEntityToDto(projectEntity);
    }

    @Transactional(readOnly = true)
    public  ProjectDto getProjectDtoById(String uuid){
        ProjectEntity projectEntity = getProjectEntityById(uuid);

        return ProjectDtoConverter.convertEntityToDto(projectEntity);
    }
    @Transactional(readOnly = true)
    public ProjectEntity getProjectEntityById(String uuid){
        return projectRepository.findById(uuid)
                .orElseThrow(()-> new NotFoundException("Проект с id " + uuid + " не найден"));
    }
    @Transactional(readOnly = true)
    public List<TaskEntity> getTasksByProject(ProjectEntity projectEntity){
        return  taskRepository.findByProjectId(projectEntity);
    }
}
