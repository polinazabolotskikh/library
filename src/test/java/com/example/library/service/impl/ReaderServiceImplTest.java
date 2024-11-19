package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.repository.ReaderRepo;
import com.example.library.model.dto.request.ReaderInfoRequest;
import com.example.library.model.dto.response.ReaderInfoResponse;
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
public class ReaderServiceImplTest {
    @InjectMocks
    private ReaderServiceImpl readerService;
    @Mock
    private ReaderRepo readerRepo;
    @Spy
    private ObjectMapper mapper;
    @Test
    public void createReader() {
        Reader reader =new Reader();
        reader.setId(1L);
        when(readerRepo.save(any(Reader.class)))
                .thenReturn(reader);
        ReaderInfoRequest request=new ReaderInfoRequest();
        request.setEmail("test@test.com");
        ReaderInfoResponse result = readerService.createReader(request);
        assertEquals(Long.valueOf(1L),result.getId());
    }
    @Test(expected = CustomException.class)
    public void createReaderInvalidEmail() {
        ReaderInfoRequest request = new ReaderInfoRequest();
        readerService.createReader(request);
    }

    @Test(expected = CustomException.class)
    public void createUserExists() {
        ReaderInfoRequest request = new ReaderInfoRequest();
        request.setEmail("test@test.com");

        Reader reader = new Reader();
        reader.setId(1L);

        when(readerRepo.findByEmail(anyString())).thenReturn(Optional.of(reader));

        readerService.createReader(request);
    }

    @Test
    public void getReader() {
    }

    @Test
    public void getReaderDb() {
    }

    @Test
    public void updateReader() {
        ReaderInfoRequest request=new ReaderInfoRequest();
        request.setAge(19);
        Reader reader=new Reader();
        reader.setId(1L);
        reader.setAge(34);
        reader.setFio("Ivanov Ivan Ivanovich");

        when(readerRepo.findById(reader.getId())).thenReturn(Optional.of(reader));
        when(readerRepo.save(any(Reader.class))).thenReturn(reader);

        ReaderInfoResponse result=readerService.updateReader(reader.getId(),request);
        assertEquals(reader.getFio(),result.getFio());
        assertEquals(request.getAge(),result.getAge());
    }

    @Test
    public void deleteReader() {
        Reader reader=new Reader();
        reader.setId(1L);
        when(readerRepo.findById(reader.getId())).thenReturn(Optional.of(reader));
        readerService.deleteReader(reader.getId());
        verify(readerRepo,times(1)).save(any(Reader.class));
        assertEquals(Status.DELETED,reader.getStatus());
    }
}