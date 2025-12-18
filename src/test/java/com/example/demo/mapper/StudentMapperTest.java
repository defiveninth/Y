package com.example.demo.mapper;

import com.example.demo.Dto.StudentDto;
import com.example.demo.model.Student;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class StudentMapperTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    void convertEntityToDtoTest() {
        Student studentEntity = new Student(1L, "Abdurrauf", 1, null, null);
        StudentDto studentDto = studentMapper.toDto(studentEntity);

        Assertions.assertNotNull(studentDto);

        Assertions.assertNotNull(studentDto.getId());
        Assertions.assertNotNull(studentDto.getName());
        Assertions.assertTrue(studentDto.getCourse() >= 1);

        Assertions.assertEquals(studentEntity.getId(), studentDto.getId());
        Assertions.assertEquals(studentEntity.getName(), studentDto.getName());
        Assertions.assertEquals(studentEntity.getCourse(), studentDto.getCourse());
    }

    @Test
    void convertDtoToEntityTest() {
        StudentDto studentDto = new StudentDto(1L, "Abdurrauf", 1, null, null);
        Student studentEntity = studentMapper.toEntity(studentDto);

        Assertions.assertNotNull(studentEntity);

        Assertions.assertNotNull(studentEntity.getId());
        Assertions.assertNotNull(studentEntity.getName());
        Assertions.assertTrue(studentEntity.getCourse() >= 1);

        Assertions.assertEquals(studentEntity.getId(), studentDto.getId());
        Assertions.assertEquals(studentEntity.getName(), studentDto.getName());
        Assertions.assertEquals(studentEntity.getCourse(), studentDto.getCourse());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Student> studentEntityList = new ArrayList<>();
        studentEntityList.add(new Student(1L, "Abdurrauf", 1, null, null));
        studentEntityList.add(new Student(2L, "Make", 1, null, null));
        studentEntityList.add(new Student(3L, "Sake", 1, null, null));

        List<StudentDto> studentDtoList = studentMapper.toDtoList(studentEntityList);

        
    }
}
