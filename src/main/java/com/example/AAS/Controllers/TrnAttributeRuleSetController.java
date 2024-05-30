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

import com.example.AAS.dto.AttributeRuleSetDto;
import com.example.AAS.service.TrnAttribureRuleSetService;

@RestController
@RequestMapping("/trn")
public class TrnAttributeRuleSetController {

	@Autowired
	private TrnAttribureRuleSetService trnService;
	
	@PostMapping
	public ResponseEntity<AttributeRuleSetDto> createTrnRuleSet(@RequestBody AttributeRuleSetDto trnAttributeRuleSetDto){
		return new ResponseEntity<>(trnService.createTrnRuleSet(trnAttributeRuleSetDto),HttpStatus.CREATED);
	}
	
	@GetMapping("/{rowId}")
	public ResponseEntity<AttributeRuleSetDto> getTrnRuleSet(@PathVariable int rowId){
		return new ResponseEntity<>(trnService.getTrnRuleSet(rowId),HttpStatus.OK);
	}
	
	
}
