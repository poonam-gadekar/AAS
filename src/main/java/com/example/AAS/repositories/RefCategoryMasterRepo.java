package com.example.AAS.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AAS.Entity.RefCategoryMaster;

public interface RefCategoryMasterRepo extends JpaRepository<RefCategoryMaster, Integer> {

	
Optional<RefCategoryMaster> findByCategory(String category);
}
