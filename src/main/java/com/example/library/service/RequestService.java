package com.example.library.service;

import com.example.library.model.db.entity.Request;
import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.dto.response.RequestInfoResponse;

public interface RequestService {
    RequestInfoResponse createRequest(RequestInfoRequest request);

    RequestInfoResponse getRequest(Long id);

    Request getRequestDb(Long id);

    RequestInfoResponse updateRequest(Long id, RequestInfoRequest request);

    void deleteRequest(Long id);
}
