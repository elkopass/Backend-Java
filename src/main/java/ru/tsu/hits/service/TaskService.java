package ru.tsu.hits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.dto.*;
import ru.tsu.hits.dto.converter.TaskDtoConverter;
import ru.tsu.hits.dto.converter.UserDtoConverter;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.Role;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.entity.UserEntity;
import ru.tsu.hits.exception.NotFoundException;
import ru.tsu.hits.repository.CommentRepository;
import ru.tsu.hits.repository.ProjectRepository;
import ru.tsu.hits.repository.TaskRepository;
import ru.tsu.hits.repository.UserRepository;

import javax.persistence.criteria.Predicate;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {



    private  final TaskRepository taskRepository;

    private  final ProjectRepository projectRepository;

    private  final CommentRepository commentRepository;

    private  final UserRepository userRepository;

    @Transactional
    public TaskDto createTask(CRUTaskDto dto){
        var creator = userRepository.findById(dto.getCreator())
                .orElseThrow(()-> new NotFoundException("Big Epic Fail"));
        var executor = userRepository.findById(dto.getExecuter())
                .orElseThrow(()-> new NotFoundException("Big Epic Fail"));
        var project = projectRepository.findById(dto.getProject())
                .orElseThrow(()-> new NotFoundException("Big Epic Fail"));
        TaskEntity taskEntity = TaskDtoConverter.convertDtoToEntity(dto, creator, executor, project);

        taskEntity = taskRepository.save(taskEntity);

        return TaskDtoConverter.convertEntityToDto(taskEntity);
    }

    @Transactional(readOnly = true)
    public  TaskDto getTaskDtoById(String uuid){
        TaskEntity taskEntity = getTaskEntityById(uuid);

        return TaskDtoConverter.convertEntityToDto(taskEntity);
    }
    @Transactional(readOnly = true)
    public TaskEntity getTaskEntityById(String uuid){
        return taskRepository.findById(uuid)
                .orElseThrow(()-> new NotFoundException("Проект с id " + uuid + " не найден"));
    }
    @Transactional
    public ArrayList<TaskDto> getTasksByProject(String id){
        ProjectEntity projectEntity = projectRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Проект с id " + id + " не найден"));
        var tmp = new ArrayList<TaskDto>();
        for (TaskEntity elem:taskRepository.findByProjectId(projectEntity)) {
            tmp.add(TaskDtoConverter.convertEntityToDto(elem)) ;
        }
        return   tmp;

    }
    @Transactional
    public ArrayList<TaskDto> getTasksByExecuter(String id){
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Executer с id " + id + " не найден"));
        var tmp = new ArrayList<TaskDto>();
        for (TaskEntity elem:taskRepository.findByExecuter(userEntity)) {
            tmp.add(TaskDtoConverter.convertEntityToDto(elem)) ;
        }
        return   tmp;

    }

    public List<TaskDto> getCommentsByText(String text){
        var comments = commentRepository.findCommentEntityByTemplate(text);
        var result = new ArrayList<TaskDto>();
        for(var comment: comments){
            var tasks = comment.getTask();
            for(var task: tasks){
                result.add(TaskDtoConverter.convertEntityToDto(task));
            }
        }

        return result;
    }


    public  List<TaskDto> fetchTasks(FetchTasksDto dto){
        return taskRepository
                .findAll(((root, query, criteriaBuilder) -> {
                    var predicates = new ArrayList<>();
                    dto.getFields().forEach((fieldName, fieldValue) ->{
                        switch (fieldName){
                            case "createDate":
                            case "editDate":
                                SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
                                Date date = null;
                                try {
                                    date = formatter.parse(fieldValue);
                                }catch (ParseException e){
                                    System.out.println(e.getMessage());
                                }
                                predicates.add(criteriaBuilder.equal(root.get(fieldName), date));
                                break;
                            case "creator":
                            case "executer":
                            case "projectId":
                                predicates.add(criteriaBuilder.equal(root.get(fieldName).get("uuid"), fieldValue));
                                break;
                            case "mark":
                            case "priority":
                                predicates.add(criteriaBuilder.equal(root.get(fieldName), fieldValue));
                                break;
                            case "name":
                            case "description":
                                predicates.add(criteriaBuilder.like(root.get(fieldName), '%' + fieldValue + '%'));
                                break;

                            default:
                                throw new RuntimeException("Несуществуещее поле поиска: " + fieldName);
                        }
                    });

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }))
                .stream()
                .map(TaskDtoConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }


//    @Transactional(readOnly = true)
//    public List<CommentEntity> getCommentsByTask(TaskEntity taskEntity){
//        return  commentRepository.findByTask(taskEntity);
//    }
}
