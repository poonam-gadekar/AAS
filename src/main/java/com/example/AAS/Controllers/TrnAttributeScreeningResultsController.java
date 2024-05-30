package com.example.AAS.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AAS.dto.AttributeScreeningResultsDto;
import com.example.AAS.service.TrnAttributeScreeningResultsService;

@RestController
@RequestMapping("/AttributeScreening")
public class TrnAttributeScreeningResultsController {

	@Autowired
	private TrnAttributeScreeningResultsService trnAttributeScreeningResultsService;

	@PostMapping
	public ResponseEntity<AttributeScreeningResultsDto> createAttributeScreeningResults(
			@RequestBody AttributeScreeningResultsDto attributeScreeningResultsDto) {

		AttributeScreeningResultsDto attributeScreeningResultsDto2 = trnAttributeScreeningResultsService
				.createAttributeScreeningResults(attributeScreeningResultsDto);
		return new ResponseEntity<>(attributeScreeningResultsDto2, HttpStatus.CREATED);

	}

	@GetMapping("/{row_id}")
	public ResponseEntity<AttributeScreeningResultsDto> getAttributeScreeningResults(@PathVariable int row_id){
		
		return new ResponseEntity<>(trnAttributeScreeningResultsService.getAttributeScreeningResults(row_id),HttpStatus.OK);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
