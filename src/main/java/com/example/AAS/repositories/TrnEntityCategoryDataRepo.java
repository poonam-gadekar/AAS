package com.example.AAS.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.AAS.Entity.TrnEntityCategoryData;

public interface TrnEntityCategoryDataRepo extends JpaRepository<TrnEntityCategoryData, Long> {

	
	  
	/*
	 * @Query(value =
	 * "select * from aas.trn_entity_category_data where mapping_id =?1",
	 * nativeQuery = true) TrnEntityCategoryData getMappingId(Long mappingId);
	 */
}
