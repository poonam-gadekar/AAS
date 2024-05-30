package com.example.AAS.Entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ref_sourse_file_type")
public class RefSourseFileType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long fileTypeId;
	private String dataSet;
	private String description;
	private String fileName;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	private String dataSourse;
	private String dataSourseIdentifier;
	
	@OneToMany(mappedBy = "refSourseFileType",cascade =CascadeType.REMOVE,fetch = FetchType.LAZY)
	private List<RefSourseDataFieldMapping> refSourseDataFieldMappings;
	
	
}
