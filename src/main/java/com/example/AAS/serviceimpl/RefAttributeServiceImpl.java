package com.example.AAS.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.RefAttribute;
import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.RefSourseDataFieldMapping;
import com.example.AAS.Entity.TrnAttributeRuleSet;
import com.example.AAS.Entity.TrnAttributeScreeningResults;
import com.example.AAS.Entity.TrnEntityCategoryData;
import com.example.AAS.dto.AtrributeDto;
import com.example.AAS.dto.RuleSetDto;
import com.example.AAS.exception.BadRequestException;
import com.example.AAS.exception.IdNotFoundException;
import com.example.AAS.repositories.RefAttributeRepo;
import com.example.AAS.repositories.RefRuleSetRepo;
import com.example.AAS.repositories.TrnAttributeScreeningResultsRepo;
import com.example.AAS.repositories.TrnAttrubuteRuleSetRepo;
import com.example.AAS.repositories.TrnEntityCategoryDataRepo;
import com.example.AAS.service.RefAttributeService;
import com.example.AAS.util.RuleUtility;

import io.micrometer.common.util.StringUtils;

@Service
public class RefAttributeServiceImpl implements RefAttributeService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RefAttributeRepo refAttributeRepo;

	@Autowired
	private RefRuleSetRepo refRuleSetRepo;

	@Autowired
	private TrnAttrubuteRuleSetRepo trnAttrubuteRuleSetRepo;

	@Autowired
	private TrnEntityCategoryDataRepo trnEntityCategoryDataRepo;
	
	
	@Autowired
	private TrnAttributeScreeningResultsRepo trnAttributeScreeningResultsRepo;

	@Override
	public AtrributeDto createAttributr(AtrributeDto refAtrributeDto) {
		// dto to entity
		AtrributeDto refAtrributeDto2 = null;
		RefAttribute refAttribute2 = mapper.map(refAtrributeDto, RefAttribute.class);

		refAttribute2 = refAttributeRepo.save(refAttribute2);

		if (refAtrributeDto.getRuleSetDtos() != null) {

			List<RuleSetDto> refRuleSetDtos = refAtrributeDto.getRuleSetDtos();

			for (RuleSetDto refRuleSetDto : refRuleSetDtos) {

				RefRuleSet refRuleSet = refRuleSetRepo.findById(refRuleSetDto.getRuleSetId()).orElse(null);

				TrnAttributeRuleSet trnAttributeRuleSet = new TrnAttributeRuleSet();
				trnAttributeRuleSet.setRefAttribute(refAttribute2);
				trnAttributeRuleSet.setRefRuleSet(refRuleSet);
				trnAttrubuteRuleSetRepo.save(trnAttributeRuleSet);
			}

		} else {

			// entity to dto
			refAtrributeDto2 = mapper.map(refAttribute2, AtrributeDto.class);
		}
		return refAtrributeDto2;

	}

	@Override
	public AtrributeDto getAttribute(Long attribute_id) {
		RefAttribute refAttribute = refAttributeRepo.findById(attribute_id)
				.orElseThrow(() -> new IdNotFoundException("given attribute id not found exception"));
		AtrributeDto refAtrributeDto = mapper.map(refAttribute, AtrributeDto.class);
		return refAtrributeDto;
	}
	

	@Override
	public AtrributeDto getRuleSetAndCategoryValues(Long attributeId) {
		RefAttribute refAttribute = refAttributeRepo.findById(attributeId).orElseThrow(() -> new IdNotFoundException("given attribute id not found exception"));

		 List<TrnAttributeRuleSet> trnAttributeRuleSets = refAttribute.getTrnAttributeRuleSets();
		 List<String> results = new ArrayList<>();
		for (TrnAttributeRuleSet trnAttributeRuleSet : trnAttributeRuleSets) {
			
			RefRuleSet refRuleSet = trnAttributeRuleSet.getRefRuleSet();
			 String ruleValue = refRuleSet.getValue();
		String condition = refRuleSet.getCondition1();
		RefSourseDataFieldMapping refSourseDataFieldMapping	=  refRuleSet.getRefSourseDataFieldMapping();
		
	List<TrnEntityCategoryData> trnEntityCategoryDatas	 =  refSourseDataFieldMapping.getTrnEntityCategoryDatas();
	for (TrnEntityCategoryData trnEntityCategoryData2 : trnEntityCategoryDatas) {
		String dataFieldVAlue = trnEntityCategoryData2.getDataFieldValue();
	
	
		
			
		String result = RuleUtility.getResult(condition, ruleValue, dataFieldVAlue);
		results.add(result);
	}
		
		}
		if(results.get(0).equalsIgnoreCase("Pass") && results.get(1).equalsIgnoreCase("Pass")) {
		String finalResult = "Pass";
		TrnAttributeScreeningResults trnAttributeScreeningResults = new TrnAttributeScreeningResults();
		trnAttributeScreeningResults.setScreeningResult(finalResult);
		trnAttributeScreeningResultsRepo.save(trnAttributeScreeningResults);
		}else
		{
			String finalResult = "Fail";
			TrnAttributeScreeningResults trnAttributeScreeningResults = new TrnAttributeScreeningResults();
			trnAttributeScreeningResults.setScreeningResult(finalResult);
			trnAttributeScreeningResultsRepo.save(trnAttributeScreeningResults);
			
		}
		return null;
	}

	
	
	
	
	
	
	
	@Override
	public List<AtrributeDto> getAllAttributes() {

		List<RefAttribute> refAttributes = refAttributeRepo.findAll();

		List<RuleSetDto> ruleSetDtos = new ArrayList<>();
		List<AtrributeDto> atrributeDtos = new ArrayList<>();

		for (RefAttribute refAttribute : refAttributes) {
			List<TrnAttributeRuleSet> trnAttributeRuleSets = refAttribute.getTrnAttributeRuleSets();

			for (TrnAttributeRuleSet trnAttributeRuleSet : trnAttributeRuleSets) {
				RefRuleSet refRuleSet = trnAttributeRuleSet.getRefRuleSet();
				RuleSetDto ruleSetDto = mapper.map(refRuleSet, RuleSetDto.class);
				ruleSetDtos.add(ruleSetDto);

			}

			AtrributeDto atrributeDto = mapper.map(refAttribute, AtrributeDto.class);
			atrributeDto.setRuleSetDtos(ruleSetDtos);
			atrributeDtos.add(atrributeDto);

		}

		return atrributeDtos;
	}

	@Override
	public List<AtrributeDto> getAttributesByCodeOrDescription(String searchParameter) {
		if (!searchParameter.isEmpty() && !(searchParameter.length() >= 3)) {
			throw new BadRequestException("source should be not null and more than length 3");
		}
		List<RefAttribute> refAttributes = refAttributeRepo.findByAttributeCodeOrDescription(searchParameter);
		return refAttributes.stream().map(refAttribute -> mapper.map(refAttribute, AtrributeDto.class))
				.collect(Collectors.toList());
	}

}
