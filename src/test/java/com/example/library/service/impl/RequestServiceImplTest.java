package com.example.library.service.impl;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.Request;
import com.example.library.model.db.repository.BookRepo;
import com.example.library.model.db.repository.ReaderRepo;
import com.example.library.model.db.repository.RequestRepo;
import com.example.library.model.dto.request.BookInfoRequest;
import com.example.library.model.dto.request.RequestInfoRequest;
import com.example.library.model.dto.response.BookInfoResponse;
import com.example.library.model.dto.response.ReaderInfoResponse;
import com.example.library.model.dto.response.RequestInfoResponse;
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
public class RequestServiceImplTest {
    @InjectMocks
    private RequestServiceImpl requestService;
    @Mock
    private RequestRepo requestRepo;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createRequest() {
        Request request =new Request();
        request.setId(1L);
        when(requestRepo.save(any(Request.class)))
                .thenReturn(request);
        RequestInfoRequest request1=new RequestInfoRequest();
        RequestInfoResponse result = requestService.createRequest(request1);
        assertEquals(Long.valueOf(1L),result.getId());
    }

    @Test
    public void getRequest() {
    }

    @Test
    public void getRequestDb() {
    }

    @Test
    public void updateRequest() {
        RequestInfoRequest request=new RequestInfoRequest();
        request.setYear(1984);
        Request request1=new Request();
        request1.setId(1L);
        request1.setYear(1976);
        request1.setNameBook("Idiot");

        when(requestRepo.findById(request1.getId())).thenReturn(Optional.of(request1));
        when(requestRepo.save(any(Request.class))).thenReturn(request1);

        RequestInfoResponse result=requestService.updateRequest(request1.getId(),request);
        assertEquals(request1.getNameBook(),result.getNameBook());
        assertEquals(request.getYear(),result.getYear());
    }

    @Test
    public void deleteRequest() {
        Request request1=new Request();
        request1.setId(1L);
        when(requestRepo.findById(request1.getId())).thenReturn(Optional.of(request1));
        requestService.deleteRequest(request1.getId());
        verify(requestRepo,times(1)).save(any(Request.class));
        assertEquals(Status.DELETED,request1.getStatus());
    }
}