package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Book;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.BookService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepo bookRepo;
    private final ObjectMapper mapper;

    @Override
    public BookInfoResponse createBook(BookInfoRequest request) {
        Book book = mapper.convertValue(request, Book.class);
        book.setStatus(Status.CREATED);
        book.setCreatedAt(LocalDateTime.now());
        book = bookRepo.save(book);
        return mapper.convertValue(book,BookInfoResponse.class);
    }

    @Override
    public BookInfoResponse getBook(Long id) {
        return mapper.convertValue(getBookDb(id), BookInfoResponse.class);
    }

    @Override
    public Book getBookDb(Long id){
        return bookRepo.findById(id).orElseThrow(()->new CustomException("Book is not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public BookInfoResponse updateBook(Long id, BookInfoRequest request) {
        Book book = getBookDb(id);
        if (book.getId() != null) {
        book.setNameBook(request.getNameBook()==null ? book.getNameBook():request.getNameBook());
        book.setTypeBook(request.getTypeBook()==null ? book.getTypeBook():request.getTypeBook());
        book.setAuthor(request.getAuthor()==null ? book.getAuthor():request.getAuthor());
        book.setCost(request.getCost()==null ? book.getCost():request.getCost());
        book.setQuantity(request.getQuantity()==null ? book.getQuantity():request.getQuantity());
        book.setYear(request.getYear()==null ? book.getYear():request.getYear());
        book.setRating(request.getRating()==null ? book.getRating():request.getRating());
        book.setStatus(Status.UPDATED);
        book.setUpdatedAt(LocalDateTime.now());
        book=bookRepo.save(book);}
        else{
            log.error("Book not found");
        }
        return mapper.convertValue(book,BookInfoResponse.class);
    }

    @Override
    public void deleteBook(Long id) {

        Book book = getBookDb(id);
        if (book.getId() != null) {
        book.setStatus(Status.DELETED);
        book.setUpdatedAt(LocalDateTime.now());
        bookRepo.save(book);}
        else{
            log.error("Book not found");
        }
    }
    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book getBook1(Long id) {
        return mapper.convertValue(getBookDb(id), Book.class);
    }
    @Override
    public BookInfoRequest convertBook(Book book){
        return mapper.convertValue(book, BookInfoRequest.class);
    }



}

