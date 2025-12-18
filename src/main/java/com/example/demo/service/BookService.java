package com.example.demo.service;

import com.example.Midka.Dto.BookDto;

import java.util.List;

public interface BookService {
    List<BookDto> getAll();
    BookDto addOne(BookDto dto);
    BookDto update(Long id, BookDto dto);
    int delete(Long id);
    BookDto getbyid(Long id);

}
