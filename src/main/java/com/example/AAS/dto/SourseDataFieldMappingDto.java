package com.example.AAS.dto;

import java.util.Date;
import java.util.List;

import com.example.AAS.Entity.RefCategoryMaster;
import com.example.AAS.Entity.RefSourseFileType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SourseDataFieldMappingDto {
	
	private Long mappingId;
	private String fileTypeId;
	private Long categoryId;
	private String dataField;
	private Date validFrom;
	private Date validTo;
	private String modifiedBy;;
	private List<RuleSetDto> ruleSetDtos;
	private RefCategoryMaster refCategoryMaster;
	private RefSourseFileType  refSourseFileType;
}
