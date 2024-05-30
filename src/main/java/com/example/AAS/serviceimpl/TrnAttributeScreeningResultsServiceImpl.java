package com.example.AAS.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.TrnAttributeScreeningResults;
import com.example.AAS.dto.AttributeScreeningResultsDto;
import com.example.AAS.exception.IdNotFoundException;
import com.example.AAS.repositories.TrnAttributeScreeningResultsRepo;
import com.example.AAS.service.TrnAttributeScreeningResultsService;

@Service
public class TrnAttributeScreeningResultsServiceImpl implements TrnAttributeScreeningResultsService{
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TrnAttributeScreeningResultsRepo trnAttributeScreeningResultsRepo;
	
	
	@Override
	public AttributeScreeningResultsDto createAttributeScreeningResults(
			AttributeScreeningResultsDto attributeScreeningResultsDto) {

		TrnAttributeScreeningResults trnAttributeScreeningResults = mapper.map(attributeScreeningResultsDto,
				TrnAttributeScreeningResults.class);
		trnAttributeScreeningResultsRepo.save(trnAttributeScreeningResults);

		AttributeScreeningResultsDto attributeScreeningResultsDto2 = mapper.map(trnAttributeScreeningResults,
				AttributeScreeningResultsDto.class);

		return attributeScreeningResultsDto2;
	}


	@Override
	public AttributeScreeningResultsDto getAttributeScreeningResults(int row_id) {
		TrnAttributeScreeningResults trnAttributeScreeningResults = trnAttributeScreeningResultsRepo.findById(row_id)
				.orElseThrow(() -> new IdNotFoundException("screening id not found exception"));
		AttributeScreeningResultsDto attributeScreeningResultsDto = mapper.map(trnAttributeScreeningResults,
				AttributeScreeningResultsDto.class);

		return attributeScreeningResultsDto;
	}
		
	
	

}

