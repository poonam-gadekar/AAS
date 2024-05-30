package com.example.AAS.service;

import java.util.List;

import com.example.AAS.dto.RuleSetDto;

public interface RefRuleSetService {

	RuleSetDto createRuleSet(RuleSetDto refRuleSetDto);

	RuleSetDto getRefRuleSet(Long rule_set_id);

	RuleSetDto createRuleSetAttribute(RuleSetDto refRuleSetDto);

	List<RuleSetDto> getAllRulesWithAttributes();

}
