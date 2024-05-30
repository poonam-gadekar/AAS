package com.example.AAS.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AttributeRuleSetDto {

	private Long rowId;

	private String criteria;
	private Long sequence;
	private String modifiedBy;

	private AtrributeDto attribute;
	private List<RuleSetDto> ruleSets;
}
