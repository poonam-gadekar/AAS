package com.example.AAS.dto;

import java.util.List;

import com.example.AAS.Entity.RefSourseDataFieldMapping;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RuleSetDto {

	private Long ruleSetId;

	private String value;
	private boolean isDataSnapshot;
	private String modifiedBy;
	private String condition1;
	private List<AtrributeDto> Attributes;
	//private RefSourseDataFieldMapping refSourseDataFieldMapping;

}
