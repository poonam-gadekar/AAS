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

import com.example.AAS.dto.EntityCategoryDataDto;
import com.example.AAS.service.TrnEntityCategoryDataService;

@RestController
@RequestMapping("/trnentitycategory")
public class TrnEntityCategoryDataController {

	@Autowired
	private TrnEntityCategoryDataService trnEntityCategoryDataService;

	@PostMapping
	public ResponseEntity<EntityCategoryDataDto> createTrnEntityCategory(
			@RequestBody EntityCategoryDataDto EntityCategoryDataDto) {
		return new ResponseEntity<>(trnEntityCategoryDataService.createTrnEntityCategory(EntityCategoryDataDto),
				HttpStatus.CREATED);

	}

	@GetMapping("/{row_id}")
	public ResponseEntity<EntityCategoryDataDto> getTrnEntityCategory(@PathVariable Long row_id) {
		return new ResponseEntity<>(trnEntityCategoryDataService.getTrnEntityCategory(row_id),HttpStatus.OK);

	}
	
	
	
}
