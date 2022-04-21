package ru.tsu.hits.controller;

import org.apache.catalina.User;
import org.springframework.web.bind.annotation.*;
import ru.tsu.hits.dto.CRUUserDto;
import ru.tsu.hits.dto.FetchUserDto;

import ru.tsu.hits.dto.UserDto;
import lombok.RequiredArgsConstructor;
import ru.tsu.hits.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {


    private  final UserService userService;

    @PostMapping
    public UserDto save(@RequestBody CRUUserDto cruUserDto){
        //System.out.println("In");
        return  userService.save(cruUserDto);
    }

    @GetMapping("/{id}")
    public UserDto getById(@PathVariable UUID id){
        return  userService.getById(id.toString());
    }

    @RequestMapping("/csv")
    @PostMapping
    public List<UserDto> saveFromCSV(){
        return  userService.saveFromCSV();
    }

    @PostMapping("/fetchUser")
    public  List<UserDto> fetchUsers(@RequestBody FetchUserDto dto){
        return userService.fetchUsers(dto);
    }
}
