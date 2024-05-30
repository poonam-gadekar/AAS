package com.example.AAS.service;

import java.util.List;

import com.example.AAS.dto.FileDto;
import com.example.AAS.dto.SourseDataFieldMappingDto;

public interface RefSourseDataFieldMappingService {

	SourseDataFieldMappingDto createDataMapping(SourseDataFieldMappingDto sourseDataFieldMappingDto);

	SourseDataFieldMappingDto getDataMapping(int mapping_id);

	List<FileDto> getDataFieldAndCount();
	

}
