package com.example.demo.Dto;

import com.example.Midka.model.Book;
import com.example.Midka.model.Group;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDto {
    private Long id;
    private String name;
    private int course;

    @Builder.Default
    @JsonIgnore
    private List<BookDto> books = List.of();

    @Builder.Default
    @JsonIgnore
    private List<GroupDto> groups = List.of();
}

