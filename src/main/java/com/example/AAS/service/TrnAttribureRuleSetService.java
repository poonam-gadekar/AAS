package com.example.AAS.service;

import com.example.AAS.dto.AttributeRuleSetDto;

public interface TrnAttribureRuleSetService {

	AttributeRuleSetDto createTrnRuleSet(AttributeRuleSetDto trnAttributeRuleSetDto);

	AttributeRuleSetDto getTrnRuleSet(int rowId);

}
