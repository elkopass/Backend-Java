package ru.tsu.hits.dto;

import lombok.Data;

import java.util.Map;

@Data
public class FetchUserDto {
    private Map<String, String> fields;
}
