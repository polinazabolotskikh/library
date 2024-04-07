package com.example.library.service.impl;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Provider;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.db.repository.ProviderRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.ProviderInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.ProviderInfoResponse;
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
public class ProviderServiceImplTest {
    @InjectMocks
    private ProviderServiceImpl providerService;
    @Mock
    private ProviderRepo providerRepo;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createProvider() {
        Provider provider =new Provider();
        provider.setId(1L);
        when(providerRepo.save(any(Provider.class)))
                .thenReturn(provider);
        ProviderInfoRequest request=new ProviderInfoRequest();
        ProviderInfoResponse result = providerService.createProvider(request);
        assertEquals(Long.valueOf(1L),result.getId());
    }

    @Test
    public void getProvider() {
    }

    @Test
    public void getProviderDb() {
    }

    @Test
    public void updateProvider() {
        ProviderInfoRequest request=new ProviderInfoRequest();
        request.setPhone("89375674534");
        Provider provider=new Provider();
        provider.setId(1L);
        provider.setPhone("89370743212");
        provider.setName("OOO Romashka");

        when(providerRepo.findById(provider.getId())).thenReturn(Optional.of(provider));
        when(providerRepo.save(any(Provider.class))).thenReturn(provider);

        ProviderInfoResponse result=providerService.updateProvider(provider.getId(),request);
        assertEquals(provider.getName(),result.getName());
        assertEquals(request.getPhone(),result.getPhone());
    }

    @Test
    public void deleteProvider() {
        Provider provider=new Provider();
        provider.setId(1L);
        when(providerRepo.findById(provider.getId())).thenReturn(Optional.of(provider));
        providerService.deleteProvider(provider.getId());
        verify(providerRepo,times(1)).save(any(Provider.class));
        assertEquals(Status.DELETED,provider.getStatus());
    }
}