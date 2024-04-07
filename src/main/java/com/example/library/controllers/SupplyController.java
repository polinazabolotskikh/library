package com.example.library.controllers;

import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.dto.request.SupplyInfoRequest;
import com.example.library.model.dto.response.RequestInfoResponse;
import com.example.library.model.dto.response.SupplyInfoResponse;
import com.example.library.service.RequestService;
import com.example.library.service.SupplyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/supplies")
@RequiredArgsConstructor
public class SupplyController {
    private final SupplyService supplyService;
    @PostMapping
    @Operation(summary = "Добавление поставки")
    public SupplyInfoResponse createSupply(@RequestBody @Valid SupplyInfoRequest request) {
        return supplyService.createSupply(request);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Редактирование поставки")
    public SupplyInfoResponse getSupply(@PathVariable Long id) {
        return supplyService.getSupply(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Обновление поставки")
    public SupplyInfoResponse updateSupply (@PathVariable Long id, @RequestBody @Valid SupplyInfoRequest request) {
        return supplyService.updateSupply(id, request);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Удаление поставки")
    public void deleteSupply(@PathVariable Long id) {
        supplyService.deleteSupply(id);
    }
}
