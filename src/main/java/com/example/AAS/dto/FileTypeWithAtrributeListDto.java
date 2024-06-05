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
public class FileTypeWithAtrributeListDto {

	private String fileType;
	private String screeningCriteria;
	List<AtrributeDetailListDto> atrributeDetailLists;

}
