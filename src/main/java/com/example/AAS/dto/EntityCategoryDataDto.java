package com.example.AAS.dto;

import java.util.Date;

import com.example.AAS.Entity.RefSourseDataFieldMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntityCategoryDataDto {
	
	private Long rowId;
	private Long batchDetailsId;
	private Long orgPermId;
	private Long soureEntityId;
	
	private String dataField;
	private String dataFieldValue;
	private String lastOverrideValue;
	private String lastOverrideComments;
	private boolean useOverride;
	private Date modifiedDate;
	private String modifiedBy;
	
	private RefSourseDataFieldMapping refSourseDataFieldMapping;
}
