package com.example.demo.service;


import com.example.demo.Dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAll();
    StudentDto addOne(StudentDto dto);

    StudentDto findbyid(Long id);

    StudentDto update(Long id, StudentDto dto);
    int delete(Long id);
}
