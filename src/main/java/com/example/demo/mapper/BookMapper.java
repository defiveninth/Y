package com.example.demo.mapper;

import com.example.Midka.Dto.BookDto;
import org.mapstruct.Mapper;


import com.example.Midka.model.Book;
import java.util.List;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDto toDto(Book book);
    Book toEntity(BookDto bookDto);

    List<BookDto> toDtoList(List<Book> bookList);
    List<Book> toEntityList(List<BookDto> bookDtoList);

}
