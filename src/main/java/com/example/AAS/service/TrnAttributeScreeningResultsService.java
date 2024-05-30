

package com.example.AAS.service;

import com.example.AAS.dto.AttributeScreeningResultsDto;

public interface TrnAttributeScreeningResultsService {

	AttributeScreeningResultsDto createAttributeScreeningResults(AttributeScreeningResultsDto attributeScreeningResultsDto);

	AttributeScreeningResultsDto getAttributeScreeningResults(int row_id);

}
