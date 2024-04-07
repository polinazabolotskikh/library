package com.example.library.service;

import com.example.library.model.db.entity.Book;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;

public interface BookService {

    BookInfoResponse createBook(BookInfoRequest request);

    BookInfoResponse getBook(Long id);

    Book getBookDb(Long id);

    BookInfoResponse updateBook(Long id, BookInfoRequest request);

    void deleteBook(Long id);
}
