package com.example.library.model.db.repository;

import com.example.library.model.db.entity.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProviderRepo extends JpaRepository<Provider,Long> {

}
