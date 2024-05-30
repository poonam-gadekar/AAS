package com.example.AAS.dto;

import java.util.Date;

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
public class AttributeScreeningResultsDto {

	private Long rowId;
	private Long batchDetailsId;
	private Long attributeId;
	private Long orgPermId;
	private String screeningResult;
	private Date reviewEffectiveDate;
	private Long currentStatusCode;
	private boolean isLatest;
	private boolean isFlagged;
	private Date modifiedDate;
	private String  modifiedBy;
	private Long fileBatchId;
	
}
