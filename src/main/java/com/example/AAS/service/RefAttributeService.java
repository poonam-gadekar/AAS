package com.example.AAS.service;

import java.util.List;

import com.example.AAS.dto.AtrributeDto;

public interface RefAttributeService {

	AtrributeDto createAttributr(AtrributeDto refAtrributeDto);

	AtrributeDto getAttribute(Long attribute_id);

	AtrributeDto getRuleSetAndCategoryValues(Long attributeId);

	List<AtrributeDto> getAllAttributes();

	List<AtrributeDto> getAttributesByCodeOrDescription(String searchParameter);

	

}
