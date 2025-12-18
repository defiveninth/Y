package com.example.demo.mapper;

import com.example.demo.Dto.BookDto;
import com.example.demo.Dto.StudentDto;
import com.example.demo.model.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.awt.print.Book;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groups", ignore = true)
    @Mapping(target = "books", ignore = true)
    StudentDto toDto(Student student);

    Student toEntity(StudentDto dto);

    List<StudentDto> toDtoList(List<Student> list);
    List<Student> toEntityList(List<StudentDto> list);
}
