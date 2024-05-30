package com.example.AAS.service;

import com.example.AAS.dto.EntityCategoryDataDto;

public interface TrnEntityCategoryDataService {

	EntityCategoryDataDto createTrnEntityCategory(EntityCategoryDataDto entityCategoryDataDto);

	EntityCategoryDataDto getTrnEntityCategory(Long row_id);

}
