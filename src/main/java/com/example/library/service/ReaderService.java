package com.example.library.service;

import com.example.library.model.db.entity.Reader;
import com.example.library.model.dto.request.ReaderInfoRequest;
import com.example.library.model.dto.response.ReaderInfoResponse;

import java.util.List;

public interface ReaderService {
    ReaderInfoResponse createReader(ReaderInfoRequest request);

    ReaderInfoResponse getReader(Long id);

    Reader getReaderDb(Long id);

    ReaderInfoResponse updateReader(Long id, ReaderInfoRequest request);

    void deleteReader(Long id);

    Reader findByCard(String card);

    List<Reader> getAllReaders();

    Reader getReader1(String card);

    ReaderInfoRequest convertReader(Reader s);
}
