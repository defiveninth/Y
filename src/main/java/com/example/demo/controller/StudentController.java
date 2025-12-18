package com.example.demo.controller;

import com.example.Midka.Dto.BookDto;
import com.example.Midka.Dto.StudentDto;
import com.example.Midka.service.impl.StudentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {
    private final StudentServiceImpl studentService;

    @GetMapping()
    private ResponseEntity<List<StudentDto>> getallSrudent(){
        return ResponseEntity.ok(studentService.getAll());
    }
    @PostMapping()
    private ResponseEntity<StudentDto> addStudent(@RequestBody StudentDto studentDto){
        return ResponseEntity.ok((studentService.addOne(studentDto)));
    }
    @GetMapping("/{id}")
    private ResponseEntity<StudentDto> getbyid(@PathVariable Long id){
        return ResponseEntity.ok(studentService.findbyid(id));

    }

    @PutMapping("/{id}")
    private ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto){
        return ResponseEntity.ok(studentService.update(id, studentDto));
    }
    @DeleteMapping("/{id}")
    private ResponseEntity<Integer> deleteStudenty(@PathVariable Long id){
        return ResponseEntity.ok(studentService.delete(id));
    }
}
