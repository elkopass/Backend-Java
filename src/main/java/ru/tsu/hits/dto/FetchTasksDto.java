package ru.tsu.hits.dto;

import lombok.Data;

import javax.persistence.ManyToMany;
import java.util.Map;

@Data
public class FetchTasksDto {
    private Map<String, String> fields;
}
