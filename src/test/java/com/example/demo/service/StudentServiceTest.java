package com.example.demo.Service;

import com.example.demo.Dto.StudentDto;
import com.example.demo.service.StudentService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class StudentServiceTest {
    @Autowired
    private StudentService studentService;

    @Test
    void getAllTest() {
        List<StudentDto> studentDtoList = studentService.getAll();

        Assertions.assertNotNull(studentDtoList);
        Assertions.assertNotEquals(0, studentDtoList.size());

        for (int i = 0; i < studentDtoList.size(); i++) {
            StudentDto studentDto = studentDtoList.get(i);

            Assertions.assertNotNull(studentDto);
            Assertions.assertNotNull(studentDto.getId());
            Assertions.assertNotNull(studentDto.getName());
            Assertions.assertTrue(studentDto.getCourse() >= 0);
            Assertions.assertNotNull(studentDto.getBooks());
            Assertions.assertNotNull(studentDto.getGroups());

            for (int j = 0; j < studentDto.getBooks().size(); j++) {
                Assertions.assertNotNull(studentDto.getBooks().get(j).getId());
                Assertions.assertNotNull(studentDto.getBooks().get(j).getName());
                Assertions.assertNotNull(studentDto.getBooks().get(j).getGenre());
            }

            for (int j = 0; j < studentDto.getGroups().size(); j++) {
                Assertions.assertNotNull(studentDto.getGroups().get(j).getId());
                Assertions.assertNotNull(studentDto.getGroups().get(j).getName());
                Assertions.assertNotNull(studentDto.getGroups().get(j).getType());
            }
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(studentService.getAll().size());
        Long someIndex = studentService.getAll().get(randomIndex).getId();
        StudentDto studentDto = studentService.findbyid(someIndex);

        Assertions.assertNotNull(studentDto);

        Assertions.assertNotNull(studentDto.getId());
        Assertions.assertNotNull(studentDto.getName());
        Assertions.assertTrue(studentDto.getCourse() >= 1);

        Assertions.assertNotNull(studentDto.getBooks());
        Assertions.assertNotNull(studentDto.getGroups());
    }

    @Test
    void addTest() {
        StudentDto studentDto = new StudentDto();
        studentDto.setName("Abdurrauf");
        studentDto.setCourse(1);

        StudentDto add = studentService.addOne(studentDto);
        Assertions.assertNotNull(add);
        Assertions.assertNotNull(add.getId());
        Assertions.assertNotNull(add.getName());
        Assertions.assertNotNull(add.getCourse());

        StudentDto added = studentService.findbyid(add.getId());
        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertNotNull(added.getName());
        Assertions.assertNotNull(added.getCourse());

        Assertions.assertEquals(add.getId(),        added.getId());
        Assertions.assertEquals(add.getName(),      added.getName());
        Assertions.assertEquals(add.getCourse(),    added.getCourse());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(studentService.getAll().size());
        Long someIndex = studentService.getAll().get(randomIndex).getId();
        StudentDto newStudent = StudentDto
                .builder()
                .id(someIndex)
                .name("Example")
                .course(3)
                .build();

        StudentDto update = studentService.update(newStudent.getId(), newStudent);
        Assertions.assertNotNull(update);
        Assertions.assertNotNull(update.getId());
        Assertions.assertNotNull(update.getName());
        Assertions.assertNotNull(update.getCourse());

        Assertions.assertEquals(newStudent.getId(), update.getId());
        Assertions.assertEquals(newStudent.getName(), update.getName());
        Assertions.assertEquals(newStudent.getCourse(), update.getCourse());

        StudentDto updated = studentService.findbyid(someIndex);
        Assertions.assertNotNull(updated);
        Assertions.assertNotNull(updated.getId());
        Assertions.assertNotNull(updated.getName());
        Assertions.assertNotNull(updated.getCourse());

        Assertions.assertEquals(update.getId(),     updated.getId());
        Assertions.assertEquals(update.getName(),   updated.getName());
        Assertions.assertEquals(update.getCourse(), updated.getCourse());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        int randomIndex = random.nextInt(studentService.getAll().size());
        Long someIndex = studentService.getAll().get(randomIndex).getId();

        studentService.delete(someIndex);
        StudentDto deleted = studentService.findbyid(someIndex);

        Assertions.assertNull(deleted);
    }
}
