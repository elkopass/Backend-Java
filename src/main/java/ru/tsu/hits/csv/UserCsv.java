package ru.tsu.hits.csv;


import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tsu.hits.entity.Role;

import java.util.Date;

@ToString
@Getter
@Setter
public class UserCsv {

    @CsvBindByPosition(position = 0)
    private String createDate;

    @CsvBindByPosition(position = 1)
    private String editDate;

    @CsvBindByPosition(position = 2)
    private String sno;

    @CsvBindByPosition(position = 3)
    private  String password;

    @CsvBindByPosition(position = 4)
    private String username;

    @CsvBindByPosition(position = 5)
    private Role role;
}
