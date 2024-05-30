package com.example.AAS.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AAS.Entity.RefSourseDataFieldMapping;
import com.example.AAS.Entity.RefSourseFileType;
import com.example.AAS.dto.SourseDataFieldMappingDto;

public interface RefSourseDataFieldMappingRepo extends JpaRepository<RefSourseDataFieldMapping, Integer>{



	List<RefSourseDataFieldMapping> findByRefSourseFileType(RefSourseFileType refSourseFileType);

	

}
