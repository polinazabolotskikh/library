package com.example.library.controllers;

import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;
    @PostMapping
    @Operation(summary = "Добавление книги")
    public BookInfoResponse createBook(@RequestBody @Valid BookInfoRequest request) {
        return bookService.createBook(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение книги")
    public BookInfoResponse getBook(@PathVariable Long id) {
        return bookService.getBook(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование книги")
    public BookInfoResponse updateBook(@PathVariable Long id, @RequestBody @Valid BookInfoRequest request) {
        return bookService.updateBook(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление книги")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
