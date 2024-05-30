package com.example.AAS.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.RefAttribute;
import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.TrnAttributeRuleSet;
import com.example.AAS.dto.AtrributeDto;
import com.example.AAS.dto.RuleSetDto;
import com.example.AAS.exception.IdNotFoundException;
import com.example.AAS.repositories.RefAttributeRepo;
import com.example.AAS.repositories.RefRuleSetRepo;
import com.example.AAS.repositories.TrnAttrubuteRuleSetRepo;
import com.example.AAS.service.RefRuleSetService;

@Service
public class RefRuleSetServiceImpl implements RefRuleSetService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RefRuleSetRepo refRuleSetRepo;
	@Autowired
	private RefAttributeRepo refAttributeRepo;
	@Autowired
	private TrnAttrubuteRuleSetRepo trnAttrubuteRuleSetRepo;

	@Override
	public RuleSetDto createRuleSet(RuleSetDto refRuleSetDto) {
		RefRuleSet refRuleSet = mapper.map(refRuleSetDto, RefRuleSet.class);
		refRuleSetRepo.save(refRuleSet);
		RuleSetDto refRuleSetDto2 = mapper.map(refRuleSet, RuleSetDto.class);
		return refRuleSetDto2;
	}

	@Override
	public RuleSetDto getRefRuleSet(Long rule_set_id) {
		RefRuleSet refRuleSet = refRuleSetRepo.findById(rule_set_id)
				.orElseThrow(() -> new IdNotFoundException("given rule id not found exception"));
		RuleSetDto refRuleSetDto = mapper.map(refRuleSet, RuleSetDto.class);

		return refRuleSetDto;
	}

	@Override
	public RuleSetDto createRuleSetAttribute(RuleSetDto refRuleSetDto) {
		RefRuleSet refRuleSet = refRuleSetRepo.findById(refRuleSetDto.getRuleSetId())
				.orElseThrow(() -> new IdNotFoundException("given id not found exception"));

		/*
		 * List<AtrributeDto> refAttributeso = refRuleSetDto.getAttributes();
		 * 
		 * for (AtrributeDto refAtrributeDto : refAttributeso) { RefAttribute
		 * refAttribute =
		 * refAttributeRepo.findById(refAtrributeDto.getAttributeId()).orElseThrow(()->
		 * new IdNotFoundException("given id not found exception"));
		 * 
		 * TrnAttributeRuleSet trnAttributeRuleSet = new TrnAttributeRuleSet();
		 * trnAttributeRuleSet.setRefRuleSet(refRuleSet);
		 * trnAttributeRuleSet.setRefAttribute(refAttribute);
		 * trnAttrubuteRuleSetRepo.save(trnAttributeRuleSet);
		 * 
		 * } RuleSetDto ruleSetDto = mapper.map(refRuleSet, RuleSetDto.class);
		 */
		return null;
	}

	@Override
	public List<RuleSetDto> getAllRulesWithAttributes() {
		List<RefRuleSet> refRuleSets = refRuleSetRepo.findAll();

		List<RuleSetDto> ruleSetDtos = new ArrayList<>();

		for (RefRuleSet refRuleSet : refRuleSets) {
			List<AtrributeDto> attrAtrributeDtos = new ArrayList<>();
			List<TrnAttributeRuleSet> trnAttributeRuleSet = refRuleSet.getTrnAttributeRuleSets();

			for (TrnAttributeRuleSet trnAttributeRuleSet2 : trnAttributeRuleSet) {

				RefAttribute refAttribute = trnAttributeRuleSet2.getRefAttribute();
				AtrributeDto atrributeDto = mapper.map(refAttribute, AtrributeDto.class);
				attrAtrributeDtos.add(atrributeDto);

			}

			RuleSetDto ruleSetDto = mapper.map(refRuleSet, RuleSetDto.class);
			ruleSetDto.setAttributes(attrAtrributeDtos);
			ruleSetDtos.add(ruleSetDto);

		}

		return ruleSetDtos;
	}

}
