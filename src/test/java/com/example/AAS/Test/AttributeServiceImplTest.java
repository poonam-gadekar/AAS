package com.example.AAS.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.AAS.Entity.RefAttribute;
import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.RefSourseDataFieldMapping;
import com.example.AAS.Entity.TrnAttributeRuleSet;
import com.example.AAS.Entity.TrnAttributeScreeningResults;
import com.example.AAS.Entity.TrnEntityCategoryData;
import com.example.AAS.dto.AtrributeDto;
import com.example.AAS.dto.RuleSetDto;
import com.example.AAS.repositories.RefAttributeRepo;
import com.example.AAS.repositories.RefRuleSetRepo;
import com.example.AAS.repositories.TrnAttributeScreeningResultsRepo;
import com.example.AAS.repositories.TrnAttrubuteRuleSetRepo;
import com.example.AAS.repositories.TrnEntityCategoryDataRepo;
import com.example.AAS.serviceimpl.RefAttributeServiceImpl;
import com.example.AAS.util.RuleUtility;

@Import({ RefAttributeServiceImpl.class, ModelMapper.class })
@ExtendWith(SpringExtension.class)
@DisplayName("Attribute Service Tests")
@SpringBootTest
public class AttributeServiceImplTest {

	@MockBean
	private RefAttributeRepo refAttributeRepo;

	@MockBean
	private RefRuleSetRepo refRuleSetRepo;

	@MockBean
	private TrnAttrubuteRuleSetRepo trnAttrubuteRuleSetRepo;

	@MockBean
	private TrnEntityCategoryDataRepo trnEntityCategoryDataRepo;

	@MockBean
	private TrnAttributeScreeningResultsRepo trnAttributeScreeningResultsRepo;

	@Autowired
	private RefAttributeServiceImpl refAttributeService;

	@Autowired
	private ModelMapper mapper;

	@Test
	@DisplayName("Find Attribute By Code And Description Test")
	void testFindAttributesByCodeOrDescription() {

		AtrributeDto expectedAttribute = getAttributeDetails();

		List<AtrributeDto> expectedAttributeList = new ArrayList<>();
		expectedAttributeList.add(expectedAttribute);
		String expectedAttributeCode = expectedAttributeList.get(0).getAttributeCode();

		// receiving value by method
		RefAttribute refAttribute = getRefAttribute();
		List<RefAttribute> expectedRefAttributeList = new ArrayList<>();
		expectedRefAttributeList.add(refAttribute);

		when(refAttributeRepo.findByAttributeCodeOrDescription("TP5")).thenReturn((expectedRefAttributeList));

		List<AtrributeDto> receivedAttributeList = refAttributeService.getAttributesByCodeOrDescription("TP5");
		String receiveAttributeCode = receivedAttributeList.get(0).getAttributeCode();

		assertNotNull(receivedAttributeList);
		assertEquals(expectedAttributeCode, receiveAttributeCode);

	}

	@Test
	@DisplayName("match ruleSet and category Values")
	void testGetRuleSetAndCategoryValues() {

		RefAttribute expectedrefAttribute = getRefAttribute();
		List<TrnAttributeRuleSet> trnAttributeRuleSets = expectedrefAttribute.getTrnAttributeRuleSets();
		List<String> results = new ArrayList<>();
		for (TrnAttributeRuleSet trnAttributeRuleSet : trnAttributeRuleSets) {
			RefRuleSet refRuleSet = trnAttributeRuleSet.getRefRuleSet();
			String ruleValue = refRuleSet.getValue();
			String condition = refRuleSet.getCondition1();
			RefSourseDataFieldMapping refSourseDataFieldMapping = refRuleSet.getRefSourseDataFieldMapping();
			List<TrnEntityCategoryData> trnEntityCategoryDatas = refSourseDataFieldMapping.getTrnEntityCategoryDatas();
			for (TrnEntityCategoryData trnEntityCategoryData2 : trnEntityCategoryDatas) {
				String dataFieldVAlue = trnEntityCategoryData2.getDataFieldValue();
				String result = RuleUtility.getResult(condition, ruleValue, dataFieldVAlue);
				results.add(result);
			}
		}
		if (results.get(0).equals("Pass") && results.get(1).equals("Pass")) {
			String finalResult = "Pass";
			TrnAttributeScreeningResults trnAttributeScreeningResults = new TrnAttributeScreeningResults();
			trnAttributeScreeningResults.setScreeningResult(finalResult);
			trnAttributeScreeningResultsRepo.save(trnAttributeScreeningResults);
		} else {
			String finalResult = "Fail";
			TrnAttributeScreeningResults trnAttributeScreeningResults = new TrnAttributeScreeningResults();
			trnAttributeScreeningResults.setScreeningResult(finalResult);
			trnAttributeScreeningResultsRepo.save(trnAttributeScreeningResults);

		}

		// receive value by method
		RefAttribute receivedrefAttribute = getRefAttribute();

		when(refAttributeRepo.findById(1L)).thenReturn(Optional.of(receivedrefAttribute));

		refAttributeService.getRuleSetAndCategoryValues(1L);

		assertNotNull(receivedrefAttribute);
		assertNotNull(expectedrefAttribute);

	}

	@Test
	@DisplayName("Get All Attributes")
	void testGetAllAttributes() {
		
	List<AtrributeDto> expectedatrributeDtos = new ArrayList<>();
		AtrributeDto atrributeDto = getAttributeDetails();
		expectedatrributeDtos.add(atrributeDto);
		
		// receive value by method
		RefAttribute refAttribute = getRefAttribute();
		List<RefAttribute> expectedRefAttributeList = new ArrayList<>();
		expectedRefAttributeList.add(refAttribute);
		
		when(refAttributeRepo.findAll()).thenReturn(expectedRefAttributeList);
		List<AtrributeDto> receivedAttributeList = refAttributeService.getAllAttributes();
		assertNotNull(receivedAttributeList);
		//assertEquals(expectedatrributeDtos, receivedAttributeList);
		
		
		
	}
	
	@Test
	@DisplayName("Get Attribute By Id")
	void testGetAttribute() {
		
		AtrributeDto atrributeDto = getAttributeDetails();
		
		
		
		// receive value by method
				RefAttribute refAttribute = getRefAttribute();
				when(refAttributeRepo.findById(1L)).thenReturn(Optional.of(refAttribute));
				AtrributeDto atrributeDto1 = refAttributeService.getAttribute(1L);
				assertNotNull(atrributeDto1, " attributes data is preesents in object");
				assertEquals(atrributeDto1.getAttributeGroup(), atrributeDto.getAttributeGroup());
		
	}
	
	
	
	
	
	
	private AtrributeDto getAttributeDetails() {
		AtrributeDto atrributeDto = getAttribute();
		return atrributeDto;
	}

	private AtrributeDto getAttribute() {
		AtrributeDto atrributeDto = new AtrributeDto();
		atrributeDto.setAttributeId((long) 1);
		atrributeDto.setAttributeGroup("Tobbaco");
		atrributeDto.setAttributeCode("TP");
		atrributeDto.setDescription("TP5");
		atrributeDto.setModifiedBy("Poonam");
		
		List<RuleSetDto> ruleSetDtos = new ArrayList<>();
		RuleSetDto ruleSetDto = getRuleSetDto(1L, "CONTAINS", "Poonam", "3,4,5");
		RuleSetDto ruleSetDto1 = getRuleSetDto(2L, "EQUALS", "Poonam", "TP5");
		ruleSetDtos.add(ruleSetDto);
		ruleSetDtos.add(ruleSetDto1);
		atrributeDto.setRuleSetDtos(ruleSetDtos);

		return atrributeDto;
	}

	private RuleSetDto getRuleSetDto(long ruleSetId, String condition, String modifiedBy, String value) {
		
		RuleSetDto ruleSetDto = new RuleSetDto();
		ruleSetDto.setRuleSetId(ruleSetId);
		ruleSetDto.setCondition1(condition);
		ruleSetDto.setModifiedBy(modifiedBy);
		ruleSetDto.setValue(value);
	
		return ruleSetDto;
	}

	private RefAttribute getRefAttribute() {

		RefAttribute refAttribute = new RefAttribute();
		refAttribute.setAttributeId((long) 1);
		refAttribute.setAttributeCode("TP");
		refAttribute.setAttributeGroup("Tobbaco");
		refAttribute.setDescription("TP5");
		refAttribute.setModifiedBy("Poonam");

		List<TrnAttributeRuleSet> trnAttributeRuleSets = new ArrayList<>();
		TrnAttributeRuleSet trnAttributeRuleSet = getTrnAttributeRuleSet(1L, "poonam",
				getRefRuleSet(1L, "CONTAINS", "Poonam", "3,4,5"));
		
		TrnAttributeRuleSet trnAttributeRuleSet1 = getTrnAttributeRuleSet(2L, "poonam",
				getRefRuleSet(2L, "EQUALS", "Poonam", "TP5"));

		trnAttributeRuleSets.add(trnAttributeRuleSet);
		trnAttributeRuleSets.add(trnAttributeRuleSet1);

		refAttribute.setTrnAttributeRuleSets(trnAttributeRuleSets);

		return refAttribute;
	}

	private TrnAttributeRuleSet getTrnAttributeRuleSet(Long rowId, String modifiedBy, RefRuleSet refRuleSet) {
		TrnAttributeRuleSet trnAttributeRuleSet = new TrnAttributeRuleSet();
		trnAttributeRuleSet.setRowId(rowId);
		trnAttributeRuleSet.setModifiedBy(modifiedBy);
		trnAttributeRuleSet.setRefRuleSet(refRuleSet);

		return trnAttributeRuleSet;
	}

	private RefRuleSet getRefRuleSet(long ruleSetId, String condition, String modifiedBy, String value) {

		RefRuleSet refRuleSet = new RefRuleSet();
		refRuleSet.setRuleSetId(ruleSetId);
		refRuleSet.setCondition1(condition);
		refRuleSet.setModifiedBy(modifiedBy);
		refRuleSet.setValue(value);
		refRuleSet.setRefSourseDataFieldMapping(getRefSourseDataFieldMapping());

		return refRuleSet;
	}

	private RefSourseDataFieldMapping getRefSourseDataFieldMapping() {
		List<TrnEntityCategoryData> trnEntityCategoryDatas = new ArrayList<>();
		TrnEntityCategoryData trnEntityCategoryData = getTrnEntityCategoryData("tobbaco", "3,4,5", "Poonam");
		TrnEntityCategoryData trnEntityCategoryData1 = getTrnEntityCategoryData("tobacco", "TP5", "Poonam");

		trnEntityCategoryDatas.add(trnEntityCategoryData);
		trnEntityCategoryDatas.add(trnEntityCategoryData1);

		RefSourseDataFieldMapping refSourseDataFieldMapping = new RefSourseDataFieldMapping();
		refSourseDataFieldMapping.setMappingId((long) 1);
		refSourseDataFieldMapping.setFileTypeId("exe");
		refSourseDataFieldMapping.setModifiedBy("Poonam");
		refSourseDataFieldMapping.setTrnEntityCategoryDatas(trnEntityCategoryDatas);
		return refSourseDataFieldMapping;
	}

	private TrnEntityCategoryData getTrnEntityCategoryData(String dataField, String dataFieldValue, String modifiedBy) {
		TrnEntityCategoryData trnEntityCategoryData = new TrnEntityCategoryData();
		trnEntityCategoryData.setDataField(dataField);
		trnEntityCategoryData.setDataFieldValue(dataFieldValue);
		trnEntityCategoryData.setModifiedBy(modifiedBy);

		return trnEntityCategoryData;
	}

}
