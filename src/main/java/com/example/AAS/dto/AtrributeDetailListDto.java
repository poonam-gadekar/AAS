package com.example.AAS.dto;

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
public class AtrributeDetailListDto {

	private String attributeCode;
	private String attributeDesc;
	List<String> rules;

}
