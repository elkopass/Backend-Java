package ru.tsu.hits.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.dto.CRUCommentDto;
import ru.tsu.hits.dto.CommentDto;
import ru.tsu.hits.dto.TaskDto;
import ru.tsu.hits.service.CommentService;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private  final CommentService commentService;

    @PostMapping
    public CommentDto createComment(@RequestBody CRUCommentDto cruCommentDto){
        return  commentService.createComment(cruCommentDto);
    }

    @GetMapping(value = "/{id}")
    public  CommentDto getComment (@PathVariable String id){
        return  commentService.getCommentDtoById(id);

    }


}
