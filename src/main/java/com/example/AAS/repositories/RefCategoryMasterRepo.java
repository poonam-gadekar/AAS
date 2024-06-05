package com.example.AAS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AAS.Entity.RefCategoryMaster;

public interface RefCategoryMasterRepo extends JpaRepository<RefCategoryMaster, Integer> {

	RefCategoryMaster findByCategory(String category);

}
