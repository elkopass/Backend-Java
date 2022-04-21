package ru.tsu.hits.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.dto.CRUProjectDto;
import ru.tsu.hits.dto.ProjectDto;
import ru.tsu.hits.service.ProjectService;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private  final ProjectService projectService;

    @PostMapping
    public ProjectDto createProject(@RequestBody CRUProjectDto cruProjectDto){
        return  projectService.createProject(cruProjectDto);
    }

    @GetMapping(value = "/{id}")
    public  ProjectDto getProject (@PathVariable String id){
        return  projectService.getProjectDtoById(id);
    }
}
