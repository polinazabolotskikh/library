package com.example.library.model.db.repository;

import com.example.library.model.db.entity.Book;
import com.example.library.model.db.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepo extends JpaRepository<User,Long> {
    @Query("SELECT u FROM User u WHERE u.login = ?1 and u.status!='DELETED'")
    User findByLogin(String login);

    @Query("SELECT n.type FROM User n WHERE n.login = ?1 and n.status!='DELETED'")
    Integer getType(String login);
}
