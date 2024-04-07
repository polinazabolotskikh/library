package com.example.library.service.impl;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Supply;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.db.repository.SupplyRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.SupplyInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.SupplyInfoResponse;
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
public class SupplyServiceImplTest {
    @InjectMocks
    private SupplyServiceImpl supplyService;
    @Mock
    private SupplyRepo supplyRepo;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createSupply() {
        Supply supply =new Supply();
        supply.setId(1L);
        when(supplyRepo.save(any(Supply.class)))
                .thenReturn(supply);
        SupplyInfoRequest request=new SupplyInfoRequest();
        SupplyInfoResponse result = supplyService.createSupply(request);
        assertEquals(Long.valueOf(1L),result.getId());
    }

    @Test
    public void getSupply() {
    }

    @Test
    public void getSupplyDb() {
    }

    @Test
    public void updateSupply() {
        SupplyInfoRequest request=new SupplyInfoRequest();
        request.setQuantity(19);
        Supply supply=new Supply();
        supply.setId(1L);
        supply.setQuantity(19);

        when(supplyRepo.findById(supply.getId())).thenReturn(Optional.of(supply));
        when(supplyRepo.save(any(Supply.class))).thenReturn(supply);

        SupplyInfoResponse result=supplyService.updateSupply(supply.getId(),request);
        assertEquals(request.getQuantity(),result.getQuantity());

    }

    @Test
    public void deleteSupply() {
        Supply supply=new Supply();
        supply.setId(1L);
        when(supplyRepo.findById(supply.getId())).thenReturn(Optional.of(supply));
        supplyService.deleteSupply(supply.getId());
        verify(supplyRepo,times(1)).save(any(Supply.class));
        assertEquals(Status.DELETED,supply.getStatus());
    }
}