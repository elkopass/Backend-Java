package ru.tsu.hits.csv;


import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@ToString
@Getter
@Setter
public class CommentCsv {

    @CsvBindByPosition(position = 0)
    private String createDate;

    @CsvBindByPosition(position = 1)
    private String editDate;

    @CsvBindByPosition(position = 2)
    private String user;

    @CsvBindByPosition(position = 3)
    private String task;

    @CsvBindByPosition(position = 4)
    private  String commentText;
}

