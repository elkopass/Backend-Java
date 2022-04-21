package ru.tsu.hits.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.dto.CRUCommentDto;
import ru.tsu.hits.dto.CRUTaskDto;
import ru.tsu.hits.dto.CommentDto;
import ru.tsu.hits.dto.TaskDto;
import ru.tsu.hits.dto.converter.CommentDtoConverter;
import ru.tsu.hits.dto.converter.TaskDtoConverter;
import ru.tsu.hits.entity.CommentEntity;
import ru.tsu.hits.entity.TaskEntity;
import ru.tsu.hits.exception.NotFoundException;
import ru.tsu.hits.repository.CommentRepository;
import ru.tsu.hits.repository.ProjectRepository;
import ru.tsu.hits.repository.TaskRepository;
import ru.tsu.hits.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private  final TaskRepository taskRepository;

    private  final UserRepository userRepository;
    private  final CommentRepository commentRepository;

    @Transactional
    public CommentDto createComment(CRUCommentDto dto){
        var user = userRepository.findById(dto.getUser())
                .orElseThrow(()-> new NotFoundException("Миша все ...... давай по новой"));

        var tasks = taskRepository.findAllById(dto.getTask());
        CommentEntity commentEntity = CommentDtoConverter.convertDtoToEntity(dto, user, tasks);

        commentEntity = commentRepository.save(commentEntity);

        return CommentDtoConverter.convertEntityToDto(commentEntity);
    }

    @Transactional(readOnly = true)
    public  CommentDto getCommentDtoById(String uuid){
        CommentEntity commentEntity = getCommentEntityById(uuid);

        return CommentDtoConverter.convertEntityToDto(commentEntity);
    }
    @Transactional(readOnly = true)
    public CommentEntity getCommentEntityById(String uuid){
        return commentRepository.findById(uuid)
                .orElseThrow(()-> new NotFoundException("Проект с id " + uuid + " не найден"));
    }

//    @Transactional(readOnly = true)
//    public List<TaskEntity> getTasksByComment(CommentEntity commentEntity){
//        return  taskRepository.findByComment(commentEntity);
//    }
}
