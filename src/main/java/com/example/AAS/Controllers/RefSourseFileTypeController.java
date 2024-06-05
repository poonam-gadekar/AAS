package com.example.AAS.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.AAS.dto.FileTypeWithAtrributeListDto;
import com.example.AAS.dto.SourseFileTypeDto;
import com.example.AAS.service.RefSourseFileTypeService;

@RestController
@RequestMapping("/soursefiletype")
public class RefSourseFileTypeController {
	
	@Autowired
	private RefSourseFileTypeService refSourseFileTypeService;
	
	@PostMapping
	public ResponseEntity<SourseFileTypeDto> createSourseFileType(@RequestBody SourseFileTypeDto sourseFileTypeDto){
		
		return new ResponseEntity<>(refSourseFileTypeService.createSourseFileType(sourseFileTypeDto),HttpStatus.CREATED);
	}
	
	
	@GetMapping
	public ResponseEntity<FileTypeWithAtrributeListDto> getFileTypeWithAtrributeList(@RequestParam String fileName,@RequestParam String category){
		
		
		return new ResponseEntity<>(refSourseFileTypeService.getFileTypeWithAtrributeList(fileName,category),HttpStatus.OK);
	}
	

}
