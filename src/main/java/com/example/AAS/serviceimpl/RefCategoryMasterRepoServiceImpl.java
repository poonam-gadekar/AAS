package com.example.AAS.serviceimpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.AAS.Entity.RefCategoryMaster;
import com.example.AAS.dto.CategoryMasterDto;
import com.example.AAS.exception.IdNotFoundException;
import com.example.AAS.repositories.RefCategoryMasterRepo;
import com.example.AAS.service.RefCategoryMasterRepoService;

@Service
public class RefCategoryMasterRepoServiceImpl implements RefCategoryMasterRepoService {

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RefCategoryMasterRepo refCategoryMasterRepo;

	@Override
	public CategoryMasterDto createCategory(CategoryMasterDto categoryMasterDto) {

		RefCategoryMaster refCategoryMaster = mapper.map(categoryMasterDto, RefCategoryMaster.class);
		refCategoryMasterRepo.save(refCategoryMaster);
		CategoryMasterDto categoryMasterDto2 = mapper.map(refCategoryMaster, CategoryMasterDto.class);

		return categoryMasterDto2;
	}

	@Override
	public CategoryMasterDto getCategory(int category_id) {

		RefCategoryMaster refCategoryMaster = refCategoryMasterRepo.findById(category_id).orElseThrow(()-> new IdNotFoundException("given category id not found exception"));
		CategoryMasterDto categoryMasterDto2 = mapper.map(refCategoryMaster, CategoryMasterDto.class);
		return categoryMasterDto2;
	}

}
