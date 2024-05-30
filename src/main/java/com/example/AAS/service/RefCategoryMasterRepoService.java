package com.example.AAS.service;

import com.example.AAS.dto.CategoryMasterDto;

public interface RefCategoryMasterRepoService {

	CategoryMasterDto createCategory(CategoryMasterDto categoryMasterDto);

	CategoryMasterDto getCategory(int category_id);

}
