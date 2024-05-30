package com.example.AAS.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AAS.dto.FileDto;
import com.example.AAS.dto.SourseDataFieldMappingDto;
import com.example.AAS.service.RefSourseDataFieldMappingService;

@RestController
@RequestMapping("/soursedatamapping")
public class RefSourseDataFieldMappingController {

	@Autowired
	private RefSourseDataFieldMappingService sourseDataFieldMappingService;

	@PostMapping
	public ResponseEntity<SourseDataFieldMappingDto> createDataMapping(
			@RequestBody SourseDataFieldMappingDto sourseDataFieldMappingDto) {
		return new ResponseEntity<>(sourseDataFieldMappingService.createDataMapping(sourseDataFieldMappingDto),
				HttpStatus.CREATED);
	}

	@GetMapping("/{mapping_id}")
	public ResponseEntity<SourseDataFieldMappingDto> getDataMapping(@PathVariable int mapping_id) {
		return new ResponseEntity<>(sourseDataFieldMappingService.getDataMapping(mapping_id), HttpStatus.OK);
	}
	
	
	@GetMapping("/getDataFieldAndCount")
public ResponseEntity<List<FileDto>> getDataFieldAndCount(){
		return new ResponseEntity<>(sourseDataFieldMappingService.getDataFieldAndCount(),HttpStatus.OK);
		
	}
}
