package com.example.library.model.db.repository;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,Long> {
    @Query("SELECT u.rating FROM Book u WHERE u.id = ?1 and u.status!='DELETED'")
    String getRating(Long id);

    @Query("SELECT u.typeBook FROM Book u WHERE u.id = ?1 and u.status!='DELETED'")
    String getType(Long id);

    @Query("SELECT u.quantity FROM Book u WHERE u.id = ?1 and u.status!='DELETED'")
    Integer getQuantity(Long id);

}
