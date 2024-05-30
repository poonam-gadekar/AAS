package com.example.AAS.Controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AAS.dto.RuleSetDto;
import com.example.AAS.service.RefRuleSetService;

@RestController
@RequestMapping("/ruleSet")
public class RefRuleSetController {

	private static final Logger LOGGER = LoggerFactory.getLogger(RefRuleSetController.class);
	
	@Autowired
	private RefRuleSetService refRuleSetService;

	@PostMapping("/createRuleSet")
	public ResponseEntity<RuleSetDto> createRuleSet(@RequestBody RuleSetDto refRuleSetDto) {
		RuleSetDto refRuleSetDto2 = refRuleSetService.createRuleSet(refRuleSetDto);
		LOGGER.info("rule is created sussfully");
		return new ResponseEntity<>(refRuleSetDto2, HttpStatus.CREATED);
	}

	@GetMapping("/{rule_set_id}")
	public ResponseEntity<RuleSetDto> getRefRuleSet(@PathVariable Long rule_set_id) {

		return new ResponseEntity<>(refRuleSetService.getRefRuleSet(rule_set_id), HttpStatus.OK);
		

	}
	
	@PostMapping("/createRuleattribute")
	public ResponseEntity<RuleSetDto> createRuleSetAttribute(@RequestBody RuleSetDto refRuleSetDto) {
		RuleSetDto refRuleSetDto2 = refRuleSetService.createRuleSetAttribute(refRuleSetDto);
		return new ResponseEntity<>(refRuleSetDto2, HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllRulesWithAttributes")
	public ResponseEntity<List<RuleSetDto>> getAllRulesWithAttributes(){
		List<RuleSetDto> ruleSetDtos = refRuleSetService.getAllRulesWithAttributes();
		return new ResponseEntity<>(ruleSetDtos,HttpStatus.OK);	
	}
	
	


}
