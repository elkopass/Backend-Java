package ru.tsu.hits.dto;

import ru.tsu.hits.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private  String id;

    private Date createDate;

    private Date editDate;

    private String sno;

    private  String password;

    private String username;

    private Role role;
}
