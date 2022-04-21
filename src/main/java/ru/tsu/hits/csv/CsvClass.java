package ru.tsu.hits.csv;

import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tsu.hits.entity.Role;

import java.util.Date;

@ToString
@Getter
@Setter
public class CsvClass {

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 0)
    private Date createDate;

    @CsvDate(value = "yyyy-MM-dd")
    @CsvBindByPosition(position = 1)
    private Date editDate;

    @CsvBindByPosition(position = 2)
    private  String sno;

    @CsvBindByPosition(position = 3)
    private  String password;

    @CsvBindByPosition(position = 4)
    private  String username;


    @CsvBindByPosition(position = 5)
    private Role role;
}
