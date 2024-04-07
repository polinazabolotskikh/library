package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Supply;
import com.example.library.model.db.repository.SupplyRepo;
import com.example.library.model.dto.request.SupplyInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.SupplyInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.SupplyService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplyServiceImpl implements SupplyService {
    private final SupplyRepo supplyRepo;
    private final ObjectMapper mapper;
    @Override
    public SupplyInfoResponse createSupply(SupplyInfoRequest request) {
        Supply supply = mapper.convertValue(request, Supply.class);
        supply.setStatus(Status.CREATED);
        supply.setCreatedAt(LocalDateTime.now());
        supply = supplyRepo.save(supply);
        return mapper.convertValue(supply, SupplyInfoResponse.class);
    }

    @Override
    public SupplyInfoResponse getSupply(Long id) {
        return mapper.convertValue(getSupplyDb(id), SupplyInfoResponse.class);

    }
    @Override
    public Supply getSupplyDb(Long id) {
        return supplyRepo.findById(id).orElseThrow(()->new CustomException("Supply is not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public SupplyInfoResponse updateSupply(Long id, SupplyInfoRequest request) {
        Supply supply = getSupplyDb(id);
        if (supply.getId() != null) {
            supply.setQuantity(request.getQuantity()==null ? supply.getQuantity():request.getQuantity());
            supply.setStatus(Status.UPDATED);
            supply.setUpdatedAt(LocalDateTime.now());
            supply=supplyRepo.save(supply);
        }
        else{
            log.error("Supply not found");
        }
        return mapper.convertValue(supply, SupplyInfoResponse.class);
    }

    @Override
    public void deleteSupply(Long id) {
        Supply supply = getSupplyDb(id);
        if (supply.getId() != null) {
            supply.setStatus(Status.DELETED);
            supply.setUpdatedAt(LocalDateTime.now());
            supplyRepo.save(supply);}
        else{
            log.error("Supply not found");
        }
    }
}
