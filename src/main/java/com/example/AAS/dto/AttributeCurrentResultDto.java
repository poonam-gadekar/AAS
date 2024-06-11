package com.example.AAS.dto;

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
public class AttributeCurrentResultDto {
	
	private Long attributeId;
	private String attributeCode;
	private String description;
	private String currentResult;
	private String expectedResult;
	

}
