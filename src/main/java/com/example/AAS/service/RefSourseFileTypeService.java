package com.example.AAS.service;

import com.example.AAS.dto.FileTypeWithAtrributeListDto;
import com.example.AAS.dto.SourseFileTypeDto;

public interface RefSourseFileTypeService {

	SourseFileTypeDto createSourseFileType(SourseFileTypeDto sourseFileTypeDto);

	FileTypeWithAtrributeListDto getFileTypeWithAtrributeList(String fileName, String category);

}
