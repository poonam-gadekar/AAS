package com.example.AAS.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.AAS.Entity.RefSourseFileType;

public interface RefSourseFileTypeRepo extends JpaRepository<RefSourseFileType, Long> {

	 

	Optional<RefSourseFileType> findByFileName(String fileName);
}
