package com.example.AAS.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.AAS.Entity.RefAttribute;
import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.TrnAttributeRuleSet;
import com.example.AAS.dto.AtrributeDto;
import com.example.AAS.dto.RuleSetDto;
import com.example.AAS.repositories.RefAttributeRepo;
import com.example.AAS.repositories.RefRuleSetRepo;
import com.example.AAS.repositories.TrnAttrubuteRuleSetRepo;
import com.example.AAS.serviceimpl.RefRuleSetServiceImpl;

@DisplayName("RuleSet Service Test ")
@SpringBootTest
public class RuleSetServiceImplTest {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RefRuleSetServiceImpl refRuleSetServiceImpl;

	@MockBean
	private RefRuleSetRepo refRuleSetRepo;

	@MockBean
	private RefAttributeRepo refAttributeRepo;

	@MockBean
	private TrnAttrubuteRuleSetRepo trnAttrubuteRuleSetRepo;

	@Test
	@DisplayName("Get Rule Set")
	void testGetRefRuleSet() {

		RuleSetDto expectedRuleSetDto = getRuleSet(1L, "CONTAINS", "Poonam", "3,4,5");

		// value received by method
		RefRuleSet refRuleSet = getRefRuleSet(1L, "CONTAINS", "Poonam", "3,4,5");
		when(refRuleSetRepo.findById(1L)).thenReturn(Optional.of(refRuleSet));
		RuleSetDto ruleSetDto = refRuleSetServiceImpl.getRefRuleSet(1L);
		assertEquals(expectedRuleSetDto.getCondition1(), ruleSetDto.getCondition1());

	}

	@Test
	@DisplayName("Get Rules With Attributes")
	void testgetAllRulesWithAttributes() {

		List<RuleSetDto> expectedRuleSetDtos = new ArrayList<>();
		RuleSetDto ruleSetDto = getRuleSet(1L, "CONTAINS", "Poonam", "3,4,5");
		RuleSetDto ruleSetDto1 = getRuleSet(2L, "EQUALS", "Poonam", "TP5");
		expectedRuleSetDtos.add(ruleSetDto);
		expectedRuleSetDtos.add(ruleSetDto1);

		/*
		 * for (RuleSetDto ruleSetDto2 : expectedRuleSetDtos) { List<AtrributeDto>
		 * trAtrributeDtos = ruleSetDto2.getAttributes();
		 * ruleSetDto2.setAttributes(trAtrributeDtos);
		 * expectedRuleSetDtos.add(ruleSetDto2); }
		 */
		// value received by method
		List<RefRuleSet> receiveRefRuleSets = new ArrayList<>();
		RefRuleSet refRuleSet = getRefRuleSet(1L, "CONTAINS", "Poonam", "3,4,5");
		RefRuleSet refRuleSet1 = getRefRuleSet(2L, "EQUALS", "Poonam", "TP5");
		receiveRefRuleSets.add(refRuleSet);
		receiveRefRuleSets.add(refRuleSet1);

		when(refRuleSetRepo.findAll()).thenReturn(receiveRefRuleSets);

		List<RuleSetDto> actualRuleSetDtos = refRuleSetServiceImpl.getAllRulesWithAttributes();

		assertNotNull(actualRuleSetDtos);
		assertNotNull(expectedRuleSetDtos);
		assertEquals(expectedRuleSetDtos.get(0).getCondition1(), actualRuleSetDtos.get(0).getCondition1());

	}

	private RuleSetDto getRuleSet(long ruleSetId, String condition, String modifiedBy, String value) {
		List<AtrributeDto> attAtrributeDtos = new ArrayList<>();
		AtrributeDto atrributeDto = getAttributeDto();
		attAtrributeDtos.add(atrributeDto);

		RuleSetDto refRuleSetDto = new RuleSetDto();
		refRuleSetDto.setRuleSetId(ruleSetId);
		refRuleSetDto.setCondition1(condition);
		refRuleSetDto.setModifiedBy(modifiedBy);
		refRuleSetDto.setValue(value);
		refRuleSetDto.setAttributes(attAtrributeDtos);
		return refRuleSetDto;
	}

	private AtrributeDto getAttributeDto() {
		AtrributeDto atrributeDto = new AtrributeDto();
		atrributeDto.setAttributeId((long) 1);
		atrributeDto.setAttributeCode("TP");
		atrributeDto.setAttributeGroup("Tobbaco");
		atrributeDto.setDescription("TP5");
		atrributeDto.setModifiedBy("Poonam");
		return atrributeDto;
	}

	private RefRuleSet getRefRuleSet(long ruleSetId, String condition, String modifiedBy, String value) {
		List<TrnAttributeRuleSet> trnAttributeRuleSets = new ArrayList<>();
		TrnAttributeRuleSet trnAttributeRuleSet = getTrnAttributeRuleSets();
		trnAttributeRuleSets.add(trnAttributeRuleSet);

		RefRuleSet refRuleSet = new RefRuleSet();
		refRuleSet.setRuleSetId(ruleSetId);
		refRuleSet.setCondition1(condition);
		refRuleSet.setModifiedBy(modifiedBy);
		refRuleSet.setValue(value);
		refRuleSet.setTrnAttributeRuleSets(trnAttributeRuleSets);

		return refRuleSet;
	}

	private TrnAttributeRuleSet getTrnAttributeRuleSets() {
		TrnAttributeRuleSet trnAttributeRuleSet = new TrnAttributeRuleSet();
		trnAttributeRuleSet.setRowId(1L);
		trnAttributeRuleSet.setRefAttribute(getRefAttribute());
		return trnAttributeRuleSet;
	}

	private RefAttribute getRefAttribute() {
		RefAttribute refAttribute = new RefAttribute();
		refAttribute.setAttributeId((long) 1);
		refAttribute.setAttributeCode("TP");
		refAttribute.setAttributeGroup("Tobbaco");
		refAttribute.setDescription("TP5");
		refAttribute.setModifiedBy("Poonam");

		return refAttribute;
	}

}
