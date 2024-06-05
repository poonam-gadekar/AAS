package com.example.AAS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.RefSourseDataFieldMapping;

public interface RefRuleSetRepo extends JpaRepository<RefRuleSet, Long> {

	List<RefRuleSet> findByRefSourseDataFieldMapping(RefSourseDataFieldMapping refSourseDataFieldMapping);

}
