package ru.tsu.hits.service;

import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import ru.tsu.hits.csv.CsvClass;
import ru.tsu.hits.dto.CRUUserDto;
import ru.tsu.hits.dto.FetchUserDto;
import ru.tsu.hits.dto.UserDto;
import ru.tsu.hits.dto.converter.UserDtoConverter;
import ru.tsu.hits.entity.Role;
import ru.tsu.hits.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsu.hits.help.Hashed;
import ru.tsu.hits.repository.UserRepository;

import javax.persistence.criteria.Predicate;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private Hashed hashed;
    @Transactional
    public UserDto save(CRUUserDto cruUserDto){
        var hashed = new Hashed();
        var entity = new UserEntity(
                UUID.randomUUID().toString(),
                cruUserDto.getCreateDate(),
                cruUserDto.getEditDate(),
                cruUserDto.getSno(),
                hashed.getHashedPassword(cruUserDto.getPassword()),
                cruUserDto.getUsername(),
                cruUserDto.getRole()
            );
        var savedEntity = userRepository.save(entity);
        return new UserDto(
                savedEntity.getUuid(),
                savedEntity.getCreateDate(),
                savedEntity.getEditDate(),
                savedEntity.getSno(),
                savedEntity.getPassword(),
                savedEntity.getUsername(),
                savedEntity.getRole()

        );
        }
    public UserDto getById(String id){
        var entity = userRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
        return new UserDto(
                entity.getUuid(),
                entity.getCreateDate(),
                entity.getEditDate(),
                entity.getSno(),
                entity.getPassword(),
                entity.getUsername(),
                entity.getRole()

        );
    }

    public List<UserDto> saveFromCSV(){
        InputStream inputStream = getClass()
                .getClassLoader().getResourceAsStream("data.csv");
        var users = new CsvToBeanBuilder<CsvClass>(new InputStreamReader(Objects.requireNonNull(inputStream)))
                .withSeparator(',')
                .withType(CsvClass.class)
                .withSkipLines(1)
                .build()
                .parse();

        List<UserDto> result = new ArrayList<>();
        users.forEach((elem) -> {
            var entity = new UserEntity(
                    UUID.randomUUID().toString(),
                    elem.getCreateDate(),
                    elem.getEditDate(),
                    elem.getSno(),
                    elem.getPassword(),
                    elem.getUsername(),
                    elem.getRole()
            );
            var savedEntity = userRepository.save(entity);
            result.add(new UserDto(
                    savedEntity.getUuid(),
                    savedEntity.getCreateDate(),
                    savedEntity.getEditDate(),
                    savedEntity.getSno(),
                    savedEntity.getPassword(),
                    savedEntity.getUsername(),
                    savedEntity.getRole()

            ));
        });
        return  result;
    }

    public  List<UserDto> fetchUsers(FetchUserDto dto){
        return userRepository
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
                            case "sno":
                            case "username":
                                predicates.add(criteriaBuilder.like(root.get(fieldName), '%' + fieldValue + '%'));
                                break;
                            case "role":
                                predicates.add(criteriaBuilder.equal(root.get(fieldName), Role.valueOf(fieldValue)));
                                break;
                            default:
                                throw new RuntimeException("Несуществуещее поле поиска: " + fieldName);
                        }
                    });

                    return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
                }))
                .stream()
                .map(UserDtoConverter::convertEntityToDto)
                .collect(Collectors.toList());
    }
}
