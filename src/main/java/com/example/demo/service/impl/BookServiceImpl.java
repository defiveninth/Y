package com.example.demo.service.impl;

import com.example.Midka.Dto.BookDto;
import com.example.Midka.mapper.BookMapper;
import com.example.Midka.repositories.BookRepository;
import com.example.Midka.service.BookService;

import com.example.Midka.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookDto> getAll() {
        return bookMapper.toDtoList(bookRepository.findAll());
    }

    @Override
    public BookDto addOne(BookDto dto) {
        return bookMapper.toDto(bookRepository.save(bookMapper.toEntity(dto)));
    }

    @Override
    public BookDto update(Long id, BookDto dto) {
        Book book = bookRepository.findById(id).orElse(null);
        if (book != null) {
            book.setGenre(dto.getGenre());
            book.setName(dto.getName());
            return bookMapper.toDto(bookRepository.save(book));
        }
        return null;
    }

    @Override
    public int delete(Long id) {
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null){
            bookRepository.delete(book);
        }
        return Math.toIntExact(book.getId());
    }

    @Override
    public BookDto getbyid(Long id){
        return bookMapper.toDto(bookRepository.findById(id).orElse(null));

    }
}
