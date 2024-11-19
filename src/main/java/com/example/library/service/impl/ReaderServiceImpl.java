package com.example.library.service.impl;

import com.example.library.exceptions.CustomException;
import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.repository.ReaderRepo;
import com.example.library.model.dto.request.ReaderInfoRequest;
import com.example.library.model.dto.response.ReaderInfoResponse;
import com.example.library.model.enums.Status;
import com.example.library.service.ReaderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ReaderServiceImpl implements ReaderService {
    private final ReaderRepo readerRepo;
    private final ObjectMapper mapper;
    @Override
    public ReaderInfoResponse createReader(ReaderInfoRequest request) {
        String email = request.getEmail();

        if (!EmailValidator.getInstance().isValid(email)) {
            throw new CustomException("Invalid email", HttpStatus.BAD_REQUEST);
        }

        readerRepo.findByEmail(email)
                .ifPresent(reader -> {
                    throw new CustomException("Email already exists", HttpStatus.CONFLICT);
                });

        Reader reader = mapper.convertValue(request, Reader.class);
        reader.setStatus(Status.CREATED);
        reader.setCreatedAt(LocalDateTime.now());
        reader = readerRepo.save(reader);
        return mapper.convertValue(reader, ReaderInfoResponse.class);
    }

    @Override
    public ReaderInfoResponse getReader(Long id) {
        return mapper.convertValue(getReaderDb(id), ReaderInfoResponse.class);
    }

    @Override
    public Reader getReaderDb(Long id) {
        return readerRepo.findById(id).orElseThrow(()->new CustomException("Reader is not found", HttpStatus.NOT_FOUND));
    }

    

    @Override
    public ReaderInfoResponse updateReader(Long id, ReaderInfoRequest request) {
        Reader reader=getReaderDb(id);
        if (reader.getId() != null) {
            reader.setLibrary_card(request.getLibrary_card()==null ? reader.getLibrary_card():request.getLibrary_card());
        reader.setFio(request.getFio()==null ? reader.getFio():request.getFio());
        reader.setAge(request.getAge()==null ? reader.getAge():request.getAge());
        reader.setPhone(request.getPhone()==null ? reader.getPhone():request.getPhone());
        reader.setEmail(request.getEmail()==null ? reader.getEmail():request.getEmail());
        reader.setStatus(Status.UPDATED);
        reader.setUpdatedAt(LocalDateTime.now());
        reader=readerRepo.save(reader);}
        else{
            log.error("Reader not found");
        }
        return mapper.convertValue(reader,ReaderInfoResponse.class);
    }

    @Override
    public void deleteReader(Long id) {
        Reader reader = getReaderDb(id);
        if (reader.getId() != null) {
        reader.setStatus(Status.DELETED);
        reader.setUpdatedAt(LocalDateTime.now());
        readerRepo.save(reader);}
        else{
            log.error("Reader not found");
        }
    }

    @Override
    public Reader findByCard(String card){
        return readerRepo.findByCard(card);

    }
    @Override
    public List<Reader> getAllReaders() {
        return readerRepo.findAll();
    }

    @Override
    public Reader getReader1(String card) {
        return mapper.convertValue(findByCard(card), Reader.class);
    }

    @Override
    public ReaderInfoRequest convertReader(Reader s){
        return mapper.convertValue(s, ReaderInfoRequest.class);
    }

}
