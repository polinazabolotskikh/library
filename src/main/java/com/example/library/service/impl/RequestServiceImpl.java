package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Request;
import com.example.library.model.db.repository.RequestRepo;
import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.RequestInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.RequestService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {
    private final RequestRepo requestRepo;
    private final ObjectMapper mapper;
    @Override
    public RequestInfoResponse createRequest(RequestInfoRequest request) {
        Request request1 = mapper.convertValue(request, Request.class);
        request1.setStatus(Status.CREATED);
        request1.setCreatedAt(LocalDateTime.now());
        request1 = requestRepo.save(request1);
        return mapper.convertValue(request1, RequestInfoResponse.class);
    }

    @Override
    public RequestInfoResponse getRequest(Long id) {
        return mapper.convertValue(getRequestDb(id), RequestInfoResponse.class);
    }
    @Override
    public Request getRequestDb(Long id) {
        return requestRepo.findById(id).orElseThrow(()->new CustomException("Request is not found", HttpStatus.NOT_FOUND));

    }

    @Override
    public RequestInfoResponse updateRequest(Long id, RequestInfoRequest request) {
        Request request1 = getRequestDb(id);
        if (request1.getId() != null) {
        request1.setNameBook(request.getNameBook()==null ? request1.getNameBook():request.getNameBook());
        request1.setAuthor(request.getAuthor()==null ? request1.getAuthor():request.getAuthor());
        request1.setYear(request.getYear()==null ? request1.getYear():request.getYear());
        request1.setQuantityNeed(request.getQuantityNeed()==null ? request1.getQuantityNeed():request.getQuantityNeed());
        request1.setStatus(Status.UPDATED);
        request1.setUpdatedAt(LocalDateTime.now());
        request1=requestRepo.save(request1);}
        else{
            log.error("Request not found");
        }
        return mapper.convertValue(request1,RequestInfoResponse.class);

    }

    @Override
    public void deleteRequest(Long id) {
        Request request = getRequestDb(id);
            if (request.getId() != null) {
        request.setStatus(Status.DELETED);
        request.setUpdatedAt(LocalDateTime.now());
        requestRepo.save(request);}
            else{
                log.error("Request not found");
            }
    }
}
