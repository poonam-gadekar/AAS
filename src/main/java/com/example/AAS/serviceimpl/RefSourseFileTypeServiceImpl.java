package com.example.AAS.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.RefSourseFileType;
import com.example.AAS.dto.SourseFileTypeDto;
import com.example.AAS.repositories.RefSourseFileTypeRepo;
import com.example.AAS.service.RefSourseFileTypeService;

@Service
public class RefSourseFileTypeServiceImpl implements RefSourseFileTypeService {

	@Autowired
	private RefSourseFileTypeRepo refSourseFileTypeRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public SourseFileTypeDto createSourseFileType(SourseFileTypeDto sourseFileTypeDto) {
		RefSourseFileType refSourseFileType = mapper.map(sourseFileTypeDto, RefSourseFileType.class);
		refSourseFileTypeRepo.save(refSourseFileType);
		SourseFileTypeDto sourseFileTypeDto2 = mapper.map(refSourseFileType, SourseFileTypeDto.class);

		return sourseFileTypeDto2;
	}

	
	
}
