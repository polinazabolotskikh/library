package com.example.library.model.db.repository;

import com.example.library.model.db.entity.Supply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplyRepo extends JpaRepository<Supply,Long> {
}
