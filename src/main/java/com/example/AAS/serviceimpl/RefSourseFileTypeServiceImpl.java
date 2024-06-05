package com.example.AAS.serviceimpl;

import java.sql.Ref;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.RefAttribute;
import com.example.AAS.Entity.RefCategoryMaster;
import com.example.AAS.Entity.RefRuleSet;
import com.example.AAS.Entity.RefSourseDataFieldMapping;
import com.example.AAS.Entity.RefSourseFileType;
import com.example.AAS.Entity.TrnAttributeRuleSet;
import com.example.AAS.dto.AtrributeDetailListDto;
import com.example.AAS.dto.FileTypeWithAtrributeListDto;
import com.example.AAS.dto.SourseFileTypeDto;
import com.example.AAS.repositories.RefCategoryMasterRepo;
import com.example.AAS.repositories.RefRuleSetRepo;
import com.example.AAS.repositories.RefSourseDataFieldMappingRepo;
import com.example.AAS.repositories.RefSourseFileTypeRepo;
import com.example.AAS.service.RefSourseFileTypeService;

@Service
public class RefSourseFileTypeServiceImpl implements RefSourseFileTypeService {

	@Autowired
	private RefSourseFileTypeRepo refSourseFileTypeRepo;
	
	@Autowired
	private RefSourseDataFieldMappingRepo sourseDataFieldMappingRepo;

	@Autowired
	private RefRuleSetRepo refRuleSetRepo;
	
	@Autowired
	private RefCategoryMasterRepo refCategoryMasterRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	
	@Override
	public SourseFileTypeDto createSourseFileType(SourseFileTypeDto sourseFileTypeDto) {
		RefSourseFileType refSourseFileType = mapper.map(sourseFileTypeDto, RefSourseFileType.class);
		refSourseFileTypeRepo.save(refSourseFileType);
		SourseFileTypeDto sourseFileTypeDto2 = mapper.map(refSourseFileType, SourseFileTypeDto.class);

		return sourseFileTypeDto2;
	}


	@Override
	public FileTypeWithAtrributeListDto getFileTypeWithAtrributeList(String fileName, String category) {
		
		FileTypeWithAtrributeListDto fileTypeWithAtrributeListDto = new FileTypeWithAtrributeListDto();
		List<String> rules = new ArrayList<>();
		
			RefSourseFileType refSourseFileType = refSourseFileTypeRepo.findByFileName(fileName);
			fileTypeWithAtrributeListDto.setFileType(refSourseFileType.getFileName());	
			
			RefCategoryMaster refCategoryMaster = refCategoryMasterRepo.findByCategory(category);
			fileTypeWithAtrributeListDto.setScreeningCriteria(refCategoryMaster.getCategory());
			   
			List<RefSourseDataFieldMapping> reffSourseDataFieldMappings = sourseDataFieldMappingRepo
					.findByRefSourseFileType(refSourseFileType);
		
			for (RefSourseDataFieldMapping refSourseDataFieldMapping : reffSourseDataFieldMappings) {
			  
          List<RefRuleSet>  refRuleSets = refRuleSetRepo.findByRefSourseDataFieldMapping(refSourseDataFieldMapping);				   
				for (RefRuleSet refRuleSet : refRuleSets) {
				List<AtrributeDetailListDto> attAtrributeDetailListDtos = new ArrayList<>();
				AtrributeDetailListDto atrributeDetailListDto = new AtrributeDetailListDto();
				List<TrnAttributeRuleSet> trnAttributeRuleSets = refRuleSet.getTrnAttributeRuleSets();
				for (TrnAttributeRuleSet trnAttributeRuleSet : trnAttributeRuleSets) {
				RefAttribute refAttribute = trnAttributeRuleSet.getRefAttribute();
					
					atrributeDetailListDto.setAttributeCode(refAttribute.getAttributeCode());
					atrributeDetailListDto.setAttributeDesc(refAttribute.getDescription());
					attAtrributeDetailListDtos.add(atrributeDetailListDto);
					fileTypeWithAtrributeListDto.setAtrributeDetailLists(attAtrributeDetailListDtos); 
					
					String dataField1 =	refRuleSet.getRefSourseDataFieldMapping().getDataField().concat("=").concat(refRuleSet.getValue());
					String criteria = trnAttributeRuleSet.getCriteria();
					rules.add(dataField1);
					if(criteria != null && !criteria.equals(" ")) {
						rules.add(criteria);
					}
					
					atrributeDetailListDto.setRules(rules);
					
					
					}
					
				}
				}   
		
		return fileTypeWithAtrributeListDto;
	}


}
