package com.example.library.controllers;

import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.request.ProviderInfoRequest;
import com.example.library.model.dto.request.ReaderInfoRequest;
import com.example.library.model.dto.response.ProviderInfoResponse;
import com.example.library.model.dto.response.ReaderInfoResponse;
import com.example.library.service.ProviderService;
import com.example.library.service.ReaderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/readers")
@RequiredArgsConstructor
public class ReaderController {
    private final ReaderService readerService;
    @PostMapping
    @Operation(summary = "Добавление читателя")
    public ReaderInfoResponse createReader(@RequestBody @Valid ReaderInfoRequest request) {
        return readerService.createReader(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получение читателя")
    public ReaderInfoResponse getReader(@PathVariable Long id) {
        return readerService.getReader(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Редактирование читателя")
    public ReaderInfoResponse updateReader (@PathVariable Long id, @RequestBody @Valid ReaderInfoRequest request) {
        return readerService.updateReader(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление читателя")
    public void deleteReader(@PathVariable Long id) {
        readerService.deleteReader(id);
    }
}
