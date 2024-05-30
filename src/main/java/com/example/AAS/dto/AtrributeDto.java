package com.example.AAS.dto;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
public class AtrributeDto {

	private Long attributeId;
	private String attributeCode;
	private String description;
	private String attributeGroup;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	private int rationaleMappingId;

	private List<RuleSetDto> RuleSetDtos;
	

}
