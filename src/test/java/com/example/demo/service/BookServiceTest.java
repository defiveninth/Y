package com.example.demo.Service;

import com.example.demo.Dto.BookDto;
import com.example.demo.service.BookService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
@Transactional
public class BookServiceTest {
    @Autowired
    private BookService bookService;

    @Test
    void getAllTest() {
        List<BookDto> bookDtoList = bookService.getAll();

        Assertions.assertNotNull(bookDtoList);
        Assertions.assertNotEquals(0, bookDtoList.size());

        for (BookDto bookDto : bookDtoList) {
            Assertions.assertNotNull(bookDto);
            Assertions.assertNotNull(bookDto.getId());
            Assertions.assertNotNull(bookDto.getName());
            Assertions.assertNotNull(bookDto.getGenre());
        }
    }

    @Test
    void getByIdTest() {
        Random random = new Random();
        List<BookDto> allBooks = bookService.getAll();
        int randomIndex = random.nextInt(allBooks.size());
        Long someId = allBooks.get(randomIndex).getId();
        
        BookDto bookDto = bookService.getbyid(someId);

        Assertions.assertNotNull(bookDto);
        Assertions.assertNotNull(bookDto.getId());
        Assertions.assertNotNull(bookDto.getName());
        Assertions.assertNotNull(bookDto.getGenre());
    }

    @Test
    void addTest() {
        BookDto bookDto = new BookDto();
        bookDto.setName("Test Book");
        bookDto.setGenre("Test Genre");

        BookDto added = bookService.addOne(bookDto);
        
        Assertions.assertNotNull(added);
        Assertions.assertNotNull(added.getId());
        Assertions.assertEquals("Test Book", added.getName());
        Assertions.assertEquals("Test Genre", added.getGenre());

        BookDto found = bookService.getbyid(added.getId());
        Assertions.assertNotNull(found);
        Assertions.assertEquals(added.getId(), found.getId());
        Assertions.assertEquals(added.getName(), found.getName());
        Assertions.assertEquals(added.getGenre(), found.getGenre());
    }

    @Test
    void updateTest() {
        Random random = new Random();
        List<BookDto> allBooks = bookService.getAll();
        int randomIndex = random.nextInt(allBooks.size());
        Long someId = allBooks.get(randomIndex).getId();

        BookDto updatedBook = BookDto.builder()
                .id(someId)
                .name("Updated Book")
                .genre("Updated Genre")
                .build();

        BookDto result = bookService.update(someId, updatedBook);
        
        Assertions.assertNotNull(result);
        Assertions.assertEquals(someId, result.getId());
        Assertions.assertEquals("Updated Book", result.getName());
        Assertions.assertEquals("Updated Genre", result.getGenre());

        BookDto found = bookService.getbyid(someId);
        Assertions.assertEquals(result.getName(), found.getName());
        Assertions.assertEquals(result.getGenre(), found.getGenre());
    }

    @Test
    void deleteTest() {
        Random random = new Random();
        List<BookDto> allBooks = bookService.getAll();
        int randomIndex = random.nextInt(allBooks.size());
        Long someId = allBooks.get(randomIndex).getId();

        bookService.delete(someId);
        BookDto deleted = bookService.getbyid(someId);

        Assertions.assertNull(deleted);
    }
}
