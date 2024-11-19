package com.example.library.service;

import com.example.library.model.db.entity.Book;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;

import java.util.List;

public interface BookService {

    BookInfoResponse createBook(BookInfoRequest request);

    BookInfoResponse getBook(Long id);

    Book getBookDb(Long id);

    BookInfoResponse updateBook(Long id, BookInfoRequest request);

    void deleteBook(Long id);

    List<Book> getAllBooks();

    Book getBook1(Long id);

    BookInfoRequest convertBook(Book book);
}
