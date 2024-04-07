package com.example.library.service;

import com.example.library.model.db.entity.Provider;
import com.example.library.model.dto.request.EmployeeInfoRequest;
import com.example.library.model.dto.request.ProviderInfoRequest;
import com.example.library.model.dto.response.ProviderInfoResponse;

public interface ProviderService {
    ProviderInfoResponse createProvider(ProviderInfoRequest request);

    ProviderInfoResponse getProvider(Long id);

    Provider getProviderDb(Long id);

    ProviderInfoResponse updateProvider(Long id, ProviderInfoRequest request);

    void deleteProvider(Long id);
}
