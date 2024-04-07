package com.example.library.service.impl;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.enums.Status;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    @InjectMocks
    private BookServiceImpl bookService;
    @Mock
    private BookRepo bookRepo;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createBook() {
       Book book =new Book();
       book.setId(1L);
       when(bookRepo.save(any(Book.class)))
               .thenReturn(book);
        BookInfoRequest request=new BookInfoRequest();
        BookInfoResponse result = bookService.createBook(request);
        assertEquals(Long.valueOf(1L),result.getId());
    }

    @Test
    public void getBook() {
    }

    @Test
    public void getBookDb() {
    }

    @Test
    public void updateBook() {
        BookInfoRequest request=new BookInfoRequest();
        request.setYear(1984);
        Book book=new Book();
        book.setId(1L);
        book.setYear(1976);
        book.setNameBook("Idiot");

        when(bookRepo.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepo.save(any(Book.class))).thenReturn(book);

        BookInfoResponse result=bookService.updateBook(book.getId(),request);
        assertEquals(book.getNameBook(),result.getNameBook());
        assertEquals(request.getYear(),result.getYear());
    }

    @Test
    public void deleteBook() {
        Book book=new Book();
        book.setId(1L);
        when(bookRepo.findById(book.getId())).thenReturn(Optional.of(book));
        bookService.deleteBook(book.getId());
        verify(bookRepo,times(1)).save(any(Book.class));
        assertEquals(Status.DELETED,book.getStatus());
    }
}