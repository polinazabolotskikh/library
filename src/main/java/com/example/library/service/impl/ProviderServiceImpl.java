package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Provider;
import com.example.library.model.db.repository.ProviderRepo;
import com.example.library.model.dto.request.ProviderInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.ProviderInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.ProviderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProviderServiceImpl implements ProviderService {
    private final ProviderRepo providerRepo;
    private final ObjectMapper mapper;
    @Override
    public ProviderInfoResponse createProvider(ProviderInfoRequest request) {
        Provider provider = mapper.convertValue(request, Provider.class);
        provider.setStatus(Status.CREATED);
        provider.setCreatedAt(LocalDateTime.now());
        provider = providerRepo.save(provider);
        return mapper.convertValue(provider,ProviderInfoResponse.class);
    }

    @Override
    public ProviderInfoResponse getProvider(Long id) {
        return mapper.convertValue(getProviderDb(id), ProviderInfoResponse.class);

    }
    @Override
    public Provider getProviderDb(Long id) {
        return providerRepo.findById(id).orElseThrow(()->new CustomException("Provider is not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public ProviderInfoResponse updateProvider(Long id, ProviderInfoRequest request) {
        Provider provider = getProviderDb(id);
        if (provider.getId() != null) {
        provider.setName(request.getName()==null ? provider.getName():request.getName());
        provider.setPhone(request.getPhone()==null ? provider.getPhone():request.getPhone());
        provider.setStatus(Status.UPDATED);
        provider.setUpdatedAt(LocalDateTime.now());
        provider=providerRepo.save(provider);}
        else{
            log.error("Provider not found");
        }
        return mapper.convertValue(provider,ProviderInfoResponse.class);

    }

    @Override
    public void deleteProvider(Long id) {
        Provider provider = getProviderDb(id);
        if (provider.getId() != null) {
        provider.setStatus(Status.DELETED);
        provider.setUpdatedAt(LocalDateTime.now());
        providerRepo.save(provider);}
        else{
        log.error("Employee not found");
    }
    }
}
