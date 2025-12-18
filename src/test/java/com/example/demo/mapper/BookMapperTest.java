package com.example.demo.mapper;

import com.example.demo.Dto.BookDto;
import com.example.demo.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class BookMapperTest {
    @Autowired
    private BookMapper bookMapper;

    @Test
    void convertEntityToDtoTest() {
        Book bookEntity = new Book(1L, "Java Programming", "Programming", null);
        BookDto bookDto = bookMapper.toDto(bookEntity);

        Assertions.assertNotNull(bookDto);
        Assertions.assertNotNull(bookDto.getId());
        Assertions.assertNotNull(bookDto.getName());
        Assertions.assertNotNull(bookDto.getGenre());

        Assertions.assertEquals(bookEntity.getId(), bookDto.getId());
        Assertions.assertEquals(bookEntity.getName(), bookDto.getName());
        Assertions.assertEquals(bookEntity.getGenre(), bookDto.getGenre());
    }

    @Test
    void convertDtoToEntityTest() {
        BookDto bookDto = new BookDto(1L, "Java Programming", "Programming");
        Book bookEntity = bookMapper.toEntity(bookDto);

        Assertions.assertNotNull(bookEntity);
        Assertions.assertNotNull(bookEntity.getId());
        Assertions.assertNotNull(bookEntity.getName());
        Assertions.assertNotNull(bookEntity.getGenre());

        Assertions.assertEquals(bookDto.getId(), bookEntity.getId());
        Assertions.assertEquals(bookDto.getName(), bookEntity.getName());
        Assertions.assertEquals(bookDto.getGenre(), bookEntity.getGenre());
    }

    @Test
    void convertEntityListToDtoListTest() {
        List<Book> bookEntityList = new ArrayList<>();
        bookEntityList.add(new Book(1L, "Java Programming", "Programming", null));
        bookEntityList.add(new Book(2L, "Python Basics", "Programming", null));
        bookEntityList.add(new Book(3L, "Data Structures", "Computer Science", null));

        List<BookDto> bookDtoList = bookMapper.toDtoList(bookEntityList);

        Assertions.assertNotNull(bookDtoList);
        Assertions.assertEquals(3, bookDtoList.size());

        for (int i = 0; i < bookEntityList.size(); i++) {
            Book bookEntity = bookEntityList.get(i);
            BookDto bookDto = bookDtoList.get(i);

            Assertions.assertEquals(bookEntity.getId(), bookDto.getId());
            Assertions.assertEquals(bookEntity.getName(), bookDto.getName());
            Assertions.assertEquals(bookEntity.getGenre(), bookDto.getGenre());
        }
    }
}
