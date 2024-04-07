package com.example.library.service;

import com.example.library.model.db.entity.Reader;
import com.example.library.model.dto.request.ReaderInfoRequest;
import com.example.library.model.dto.response.ReaderInfoResponse;

public interface ReaderService {
    ReaderInfoResponse createReader(ReaderInfoRequest request);

    ReaderInfoResponse getReader(Long id);

    Reader getReaderDb(Long id);

    ReaderInfoResponse updateReader(Long id, ReaderInfoRequest request);

    void deleteReader(Long id);
}
