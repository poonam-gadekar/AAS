package com.example.AAS.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryMasterDto {
	private Long categoryId;
	private String categoryCode;
	private String category;
	private String subCategory;
	private Date modifiedDate;
	private String modifiedBy;
}
