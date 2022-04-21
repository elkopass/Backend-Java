package ru.tsu.hits.csv;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@ToString
@Getter
@Setter
public class TaskCsv {

    @CsvBindByPosition(position = 0)
    private String createDate;

    @CsvBindByPosition(position = 1)
    private String editDate;

    @CsvBindByPosition(position = 2)
    private String name;

    @CsvBindByPosition(position = 3)
    private  String description;

    @CsvBindByPosition(position = 4)
    private String creator;

    @CsvBindByPosition(position = 5)
    private  String executer;

    @CsvBindByPosition(position = 6)
    private  String priority;

    @CsvBindByPosition(position = 7)
    private String Project;

    @CsvBindByPosition(position = 8)
    private  String mark;
}

