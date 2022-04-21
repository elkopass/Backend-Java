package ru.tsu.hits.dto;

import ru.tsu.hits.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class CRUUserDto {

    private Date createDate;

    private Date editDate;

    private String sno;

    private  String password;

    private String username;

    private Role role;
}
