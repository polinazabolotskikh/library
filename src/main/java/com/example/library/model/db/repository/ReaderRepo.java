package com.example.library.model.db.repository;

import com.example.library.model.db.entity.Reader;
import com.example.library.model.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReaderRepo extends JpaRepository<Reader,Long> {

    Optional<Reader> findByEmail(String email);

    @Query("SELECT u FROM Reader u WHERE u.library_card = ?1 and u.status!='DELETED'")
    Reader findByCard(String card);

    @Query("SELECT u.age FROM Reader u WHERE u.library_card = ?1 and u.status!='DELETED'")
    Integer findAge(String card);

}
