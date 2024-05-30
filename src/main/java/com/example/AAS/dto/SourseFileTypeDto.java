package com.example.AAS.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SourseFileTypeDto {

	private Long fileTypeId;
	private String dataSet;
	private String description;
	private String fileName;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	private String dataSourse;
	private String dataSourseIdentifier;

}
