package com.example.demo.service.impl;

import com.example.Midka.Dto.StudentDto;
import com.example.Midka.mapper.BookMapper;
import com.example.Midka.mapper.GroupMapper;
import com.example.Midka.mapper.StudentMapper;
import com.example.Midka.model.Student;
import com.example.Midka.repositories.BookRepository;
import com.example.Midka.repositories.GroupRepository;
import com.example.Midka.repositories.StudentRepository;
import com.example.Midka.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;
    private final GroupMapper groupMapper;
    private final BookMapper bookMapper;

    public StudentServiceImpl(StudentRepository studentRepository, BookRepository bookRepository, GroupRepository groupRepository, StudentMapper studentMapper, GroupMapper groupMapper, BookMapper bookMapper) {
        this.studentRepository = studentRepository;
        this.studentMapper = studentMapper;
        this.groupMapper = groupMapper;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<StudentDto> getAll() {
        return studentMapper.toDtoList(studentRepository.findAll());
    }

    @Override
    public StudentDto addOne(StudentDto dto) {
        return studentMapper.toDto(studentRepository.save(studentMapper.toEntity(dto)));
    }


    @Override
    public StudentDto findbyid(Long id){
        return  studentMapper.toDto(studentRepository.findById(id).orElse(null));
    }

    @Override
    public StudentDto update(Long id, StudentDto dto) {
        Student currentStudent = studentRepository.findById(id).orElse(null);
        if(currentStudent != null) {

            currentStudent.setName(dto.getName());
            currentStudent.setCourse(dto.getCourse());
            if (dto.getGroups() != null) {
                currentStudent.setGroups(
                        groupMapper.toEntityList(dto.getGroups())
                );
            }

            if (dto.getBooks() != null) {
                currentStudent.setBooks(
                        bookMapper.toEntityList(dto.getBooks()));
            }


            return studentMapper.toDto(studentRepository.save(currentStudent));
        }
        return null;
    }



    @Override
    public int delete(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if(student != null){
            studentRepository.delete(student);
        }
        return Math.toIntExact(student.getId());
    }
}
