package ru.tsu.hits.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.tsu.hits.service.CSVService;

@RestController
@RequestMapping("/csv")
@RequiredArgsConstructor
public class CSVController {

    private  final CSVService csvService;
    @GetMapping
    public void csvDone(){
        csvService.CSVMainLoad();
    }
}
