package com.example.AAS.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.AAS.Entity.RefCategoryMaster;
import com.example.AAS.Entity.RefSourseDataFieldMapping;
import com.example.AAS.Entity.RefSourseFileType;
import com.example.AAS.dto.FileDto;
import com.example.AAS.repositories.RefSourseDataFieldMappingRepo;
import com.example.AAS.repositories.RefSourseFileTypeRepo;
import com.example.AAS.serviceimpl.RefSourseDataFieldMappingServiceImpl;

@SpringBootTest
public class RefSourseDataFieldMappingTest {

	@MockBean
	private RefSourseFileTypeRepo refSourseFileTypeRepo;

	@MockBean
	private RefSourseDataFieldMappingRepo sourseDataFieldMappingRepo;

	@Autowired
	RefSourseDataFieldMappingServiceImpl refSourseDataFieldMappingService;

	@Test
	void testGetDataFieldAndCount() {

		//List<RefSourseFileType> expectedrefSourseFileTypes = getRefSourseFileType();
	
		
		
		
		//get data from method
		List<RefSourseFileType> receivedrefSourseFileTypes = getRefSourseFileType();
		for (RefSourseFileType refSourseFileType : receivedrefSourseFileTypes) {
			RefCategoryMaster refCategoryMaster = getCategoryMasterdata();
			
			List<RefSourseDataFieldMapping> refSourseDataFieldMappings = getdatasourceFieldMapping(refSourseFileType,refCategoryMaster);

		when(refSourseFileTypeRepo.findAll()).thenReturn(receivedrefSourseFileTypes);
		when(sourseDataFieldMappingRepo.findByRefSourseFileType(refSourseFileType)).thenReturn(refSourseDataFieldMappings);
		List<FileDto> fileDtos	 = refSourseDataFieldMappingService.getDataFieldAndCount();
		System.out.println(fileDtos.size());
		assertNotNull(receivedrefSourseFileTypes);
		assertNotNull(fileDtos.size());
		
		}
	}
	
	
	

	private RefCategoryMaster getCategoryMasterdata() {
		RefCategoryMaster refCategoryMaster = new RefCategoryMaster();
		refCategoryMaster.setCategoryId(1L);
		refCategoryMaster.setCategory("Abortion");
		refCategoryMaster.setCategoryCode("AB");
		refCategoryMaster.setSubCategory("Opeartions");
		return refCategoryMaster;
	}

	private List<RefSourseDataFieldMapping> getdatasourceFieldMapping(RefSourseFileType refSourseFileType, RefCategoryMaster refCategoryMaster) {
		List<RefSourseDataFieldMapping> refSourseDataFieldMappings = new ArrayList<>();
		RefSourseDataFieldMapping refSourseDataFieldMapping = 
				getRefFieldMapping(1L,"Abortion Operations-Category Of Involvement Id","poonam",refSourseFileType,refCategoryMaster);
		refSourseDataFieldMappings.add(refSourseDataFieldMapping);
		return refSourseDataFieldMappings;
	}

	private RefSourseDataFieldMapping getRefFieldMapping(long mappingId, String dataField, String modifiesBy, RefSourseFileType refSourseFileType, RefCategoryMaster refCategoryMaster) {
		RefSourseDataFieldMapping refSourseDataFieldMapping = new RefSourseDataFieldMapping();
		refSourseDataFieldMapping.setMappingId(mappingId);
		refSourseDataFieldMapping.setDataField(dataField);
		refSourseDataFieldMapping.setModifiedBy(modifiesBy);
		refSourseDataFieldMapping.setRefSourseFileType(refSourseFileType);
		refSourseDataFieldMapping.setRefCategoryMaster(refCategoryMaster);
		return refSourseDataFieldMapping;
	}

	private List<RefSourseFileType> getRefSourseFileType() {
		List<RefSourseFileType> refSourseFileTypes = new ArrayList<>();
		RefSourseFileType refSourseFileType = 
				getFileType(1L, "SUSTAIN ANALYTICS", "Sourse Dataset", "SA_P_I_A_B","Poonam");
		RefSourseFileType refSourseFileType1 = 
				getFileType(2L, "SUSTAIN ANALYTICS", "Sourse Dataset","SA_PI_ALLCATEGORIES", "Poonam");
		refSourseFileTypes.add(refSourseFileType);
		refSourseFileTypes.add(refSourseFileType1);
		return refSourseFileTypes;
	}

	private RefSourseFileType getFileType(long fileTypeId, String dataSet, String description, String fileName,
			String modifiedBy) {
		RefSourseFileType refSourseFileType = new RefSourseFileType();
		refSourseFileType.setFileTypeId(fileTypeId);
		refSourseFileType.setDataSet(dataSet);
		refSourseFileType.setDescription(description);
		refSourseFileType.setFileName(fileName);
		refSourseFileType.setModifiedBy(modifiedBy);
		
		return refSourseFileType;
	}

	

}
