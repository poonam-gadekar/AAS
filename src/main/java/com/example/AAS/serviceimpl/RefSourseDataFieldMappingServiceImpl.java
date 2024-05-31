package com.example.AAS.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.RefCategoryMaster;
import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.RefSourseDataFieldMapping;
import com.example.AAS.Entity.RefSourseFileType;
import com.example.AAS.dto.FileDto;
import com.example.AAS.dto.ItemDto;
import com.example.AAS.dto.RuleSetDto;
import com.example.AAS.dto.SourseDataFieldMappingDto;
import com.example.AAS.exception.IdNotFoundException;
import com.example.AAS.repositories.RefRuleSetRepo;
import com.example.AAS.repositories.RefSourseDataFieldMappingRepo;
import com.example.AAS.repositories.RefSourseFileTypeRepo;
import com.example.AAS.service.RefSourseDataFieldMappingService;

@Service
public class RefSourseDataFieldMappingServiceImpl implements RefSourseDataFieldMappingService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RefSourseFileTypeRepo refSourseFileTypeRepo;

	@Autowired
	private RefSourseDataFieldMappingRepo sourseDataFieldMappingRepo;

	@Autowired
	private RefRuleSetRepo refRuleSetRepo;

	@Override
	public SourseDataFieldMappingDto createDataMapping(SourseDataFieldMappingDto sourseDataFieldMappingDto) {

		SourseDataFieldMappingDto sourseDataFieldMappingDto2 = null;
		RefSourseDataFieldMapping refSourseDataFieldMapping = mapper.map(sourseDataFieldMappingDto,
				RefSourseDataFieldMapping.class);
		refSourseDataFieldMapping = sourseDataFieldMappingRepo.save(refSourseDataFieldMapping);

		if (sourseDataFieldMappingDto.getRuleSetDtos() != null) {

			List<RuleSetDto> ruleSetDtos = sourseDataFieldMappingDto.getRuleSetDtos();
			for (RuleSetDto ruleSetDto : ruleSetDtos) {
				RefRuleSet refRuleSet = refRuleSetRepo.findById(ruleSetDto.getRuleSetId()).get();
				refRuleSet.setRefSourseDataFieldMapping(refSourseDataFieldMapping);
				refRuleSetRepo.save(refRuleSet);
			}
		} else {

			sourseDataFieldMappingDto2 = mapper.map(refSourseDataFieldMapping, SourseDataFieldMappingDto.class);
		}

		return sourseDataFieldMappingDto2;
	}

	@Override
	public SourseDataFieldMappingDto getDataMapping(int mapping_id) {

		RefSourseDataFieldMapping refSourseDataFieldMapping = sourseDataFieldMappingRepo.findById(mapping_id)
				.orElseThrow(() -> new IdNotFoundException("given source field id not found exception"));
		SourseDataFieldMappingDto sourseDataFieldMappingDto2 = mapper.map(refSourseDataFieldMapping,
				SourseDataFieldMappingDto.class);

		return sourseDataFieldMappingDto2;
	}

	@Override
	public List<FileDto> getDataFieldAndCount() {

		List<FileDto> fileDtos = new ArrayList<>();

		List<RefSourseFileType> refSourseFileTypes = refSourseFileTypeRepo.findAll();

		for (RefSourseFileType refSourseFileType : refSourseFileTypes) {
			List<ItemDto> items = new ArrayList<>();
			FileDto fileDto = new FileDto();
			fileDto.setFileType(refSourseFileType.getFileName());

			List<RefSourseDataFieldMapping> reffSourseDataFieldMappings = sourseDataFieldMappingRepo
					.findByRefSourseFileType(refSourseFileType);

			Map<RefCategoryMaster, Long> counts = reffSourseDataFieldMappings.stream().collect(
					Collectors.groupingBy(RefSourseDataFieldMapping::getRefCategoryMaster, Collectors.counting()));

			for (Entry<RefCategoryMaster, Long> count : counts.entrySet()) {

				ItemDto itemDto = new ItemDto();
				String category = count.getKey().getCategory();
				Long cnt = count.getValue();
				itemDto.setCategory(category);
				itemDto.setCount(cnt);
				items.add(itemDto);

				fileDto.setItems(items);

			}
			fileDtos.add(fileDto);
		}

		return fileDtos;
	}
}
