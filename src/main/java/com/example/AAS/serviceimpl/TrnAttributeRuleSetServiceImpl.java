package com.example.AAS.serviceimpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.RefAttribute;
import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.TrnAttributeRuleSet;
import com.example.AAS.dto.AttributeRuleSetDto;
import com.example.AAS.dto.RuleSetDto;
import com.example.AAS.exception.IdNotFoundException;
import com.example.AAS.repositories.RefAttributeRepo;
import com.example.AAS.repositories.RefRuleSetRepo;
import com.example.AAS.repositories.TrnAttrubuteRuleSetRepo;
import com.example.AAS.service.TrnAttribureRuleSetService;

@Service
public class TrnAttributeRuleSetServiceImpl implements TrnAttribureRuleSetService {

	@Autowired
	private TrnAttrubuteRuleSetRepo trnAttrubuteRuleSetRepo;

	@Autowired
	private RefAttributeRepo refAttributeRepo;

	@Autowired
	private RefRuleSetRepo refRuleSetRepo;

	@Autowired
	private ModelMapper mapper;

	@Override
	public AttributeRuleSetDto createTrnRuleSet(AttributeRuleSetDto trnAttributeRuleSetDto) {
		TrnAttributeRuleSet trnRuleSet = mapper.map(trnAttributeRuleSetDto, TrnAttributeRuleSet.class);

		RefAttribute rerAttribute = refAttributeRepo.findById(trnAttributeRuleSetDto.getAttribute().getAttributeId())
				.get();
		trnRuleSet.setRefAttribute(rerAttribute);

		List<RuleSetDto> refRuleSetDtos = trnAttributeRuleSetDto.getRuleSets();
		for (RuleSetDto refRuleSetDto : refRuleSetDtos) {
			RefRuleSet refRuleSet = refRuleSetRepo.findById(refRuleSetDto.getRuleSetId()).get();

			trnRuleSet.setRefRuleSet(refRuleSet);
			trnAttrubuteRuleSetRepo.save(trnRuleSet);

		}

		AttributeRuleSetDto trnAttributeRuleSetDto2 = mapper.map(trnRuleSet, AttributeRuleSetDto.class);
		return trnAttributeRuleSetDto2;

	}

	@Override
	public AttributeRuleSetDto getTrnRuleSet(int rowId) {
		TrnAttributeRuleSet trnRuleSet = trnAttrubuteRuleSetRepo.findById(rowId).orElseThrow(()-> new IdNotFoundException("given trn id not found exception"));
		AttributeRuleSetDto trnAttributeRuleSetDto2 = mapper.map(trnRuleSet, AttributeRuleSetDto.class);
		return trnAttributeRuleSetDto2;
	}

}
