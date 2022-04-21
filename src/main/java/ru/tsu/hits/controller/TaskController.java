package ru.tsu.hits.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.dto.*;
import ru.tsu.hits.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private  final TaskService taskService;

    @PostMapping
    public TaskDto createTask(@RequestBody CRUTaskDto cruTaskDto){
        return  taskService.createTask(cruTaskDto);
    }

    @GetMapping(value = "/{id}")
    public  TaskDto getTask (@PathVariable String id){
        return  taskService.getTaskDtoById(id);
    }

    @GetMapping(value = "/byProject/{id}")
    public List<TaskDto> getTasksByProject(@PathVariable String id){
        return  taskService.getTasksByProject(id);
    }

    @GetMapping(value = "/byExecuter/{id}")
    public List<TaskDto> getTasksByExecuter(@PathVariable String id){
        return  taskService.getTasksByExecuter(id);
    }

    @GetMapping(value = "/getCommentsByText")
    public List<TaskDto> getCommentsByText (@RequestParam String commentText){
        return taskService.getCommentsByText(commentText);
    }

    @PostMapping("/fetchTask")
    public  List<TaskDto> fetchUsers(@RequestBody FetchTasksDto dto){
        return taskService.fetchTasks(dto);
    }


}
