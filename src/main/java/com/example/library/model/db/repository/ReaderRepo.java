package com.example.library.model.db.repository;

import com.example.library.model.db.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepo extends JpaRepository<Reader,Long> {

    Optional<Reader> findByEmail(String email);
}
