package ru.tsu.hits.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import ru.tsu.hits.entity.TaskEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
public class ProjectCsv {


    @CsvBindByPosition(position = 0)
    private String createDate;


    @CsvBindByPosition(position = 1)
    private String editDate;

    @CsvBindByPosition(position = 2)
    private String name;

    @CsvBindByPosition(position = 3)
    private  String description;

//    @CsvBindByPosition(position = 4)
//    private List<String> tasks;


}

