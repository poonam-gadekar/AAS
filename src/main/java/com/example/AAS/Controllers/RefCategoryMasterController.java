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

import com.example.AAS.dto.CategoryMasterDto;
import com.example.AAS.service.RefCategoryMasterRepoService;

@RestController
@RequestMapping("/category")
public class RefCategoryMasterController {

	@Autowired
	private RefCategoryMasterRepoService refCategoryMasterservice;

	@PostMapping
	public ResponseEntity<CategoryMasterDto> createCategory(@RequestBody CategoryMasterDto categoryMasterDto) {
		return new ResponseEntity<>(refCategoryMasterservice.createCategory(categoryMasterDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{category_id}")
	public ResponseEntity<CategoryMasterDto> getCategory(@PathVariable int category_id) {
		return new ResponseEntity<>(refCategoryMasterservice.getCategory(category_id),HttpStatus.OK);
	}
	
}
