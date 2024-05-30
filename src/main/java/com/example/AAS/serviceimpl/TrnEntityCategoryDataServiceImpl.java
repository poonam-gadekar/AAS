package com.example.AAS.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.TrnEntityCategoryData;
import com.example.AAS.dto.EntityCategoryDataDto;
import com.example.AAS.exception.IdNotFoundException;
import com.example.AAS.repositories.TrnEntityCategoryDataRepo;
import com.example.AAS.service.TrnEntityCategoryDataService;

@Service
public class TrnEntityCategoryDataServiceImpl implements TrnEntityCategoryDataService {
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TrnEntityCategoryDataRepo trnEntityCategoryDataRepo;
	

	@Override
	public EntityCategoryDataDto createTrnEntityCategory(EntityCategoryDataDto entityCategoryDataDto) {
		TrnEntityCategoryData  trnEntityCategoryData = mapper.map(entityCategoryDataDto, TrnEntityCategoryData.class);
		trnEntityCategoryDataRepo.save(trnEntityCategoryData);
		EntityCategoryDataDto categoryDataDto = mapper.map(trnEntityCategoryData, EntityCategoryDataDto.class);
		
		
		return categoryDataDto;
	}


	@Override
	public EntityCategoryDataDto getTrnEntityCategory(Long row_id) {
		TrnEntityCategoryData  trnEntityCategoryData = trnEntityCategoryDataRepo.findById(row_id).orElseThrow(()-> new IdNotFoundException("given  id not found exception"));
		EntityCategoryDataDto categoryDataDto = mapper.map(trnEntityCategoryData, EntityCategoryDataDto.class);
		
		return categoryDataDto;
	}
	

}
