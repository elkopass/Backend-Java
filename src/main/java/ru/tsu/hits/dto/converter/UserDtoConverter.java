package ru.tsu.hits.dto.converter;

import ru.tsu.hits.dto.CRUUserDto;
import ru.tsu.hits.dto.UserDto;
import ru.tsu.hits.entity.ProjectEntity;
import ru.tsu.hits.entity.UserEntity;
import ru.tsu.hits.entity.UserEntity;
import ru.tsu.hits.repository.UserRepository;

import java.util.UUID;

public class UserDtoConverter {
    public static UserEntity convertDtoToEntity(CRUUserDto dto, UserEntity creator, UserEntity executer, ProjectEntity project){
        UserEntity UserEntity  = new UserEntity();
        UserRepository userRepository ;


        UserEntity.setUuid(UUID.randomUUID().toString());
        UserEntity.setCreateDate(dto.getCreateDate());
        UserEntity.setEditDate(dto.getEditDate());
        UserEntity.setSno(dto.getSno());
        UserEntity.setUsername(dto.getUsername());
        UserEntity.setPassword(dto.getPassword());
        UserEntity.setRole(dto.getRole());


        return  UserEntity;
    }

    public  static UserDto convertEntityToDto(UserEntity UserEntity){

        UserDto UserDto = new UserDto();

        UserDto.setId(UserEntity.getUuid());
        UserDto.setCreateDate(UserEntity.getCreateDate());
        UserDto.setEditDate(UserEntity.getEditDate());
        UserDto.setSno(UserEntity.getSno());
        UserDto.setUsername(UserEntity.getUsername());
        UserDto.setPassword(UserEntity.getPassword());
        UserDto.setRole(UserEntity.getRole());

        return UserDto;
    }
}
