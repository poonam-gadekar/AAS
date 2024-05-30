package com.example.AAS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.AAS.Entity.RefAttribute;

public interface RefAttributeRepo extends JpaRepository<RefAttribute, Long> {
	
	
	@Query(value = "select * from aas.ref_attribute where attribute_code like %?1% or description like %?1% ", nativeQuery = true)
	List<RefAttribute> findByAttributeCodeOrDescription(String searchParameter);

}
