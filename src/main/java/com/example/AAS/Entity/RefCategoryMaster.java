package com.example.AAS.Entity;

import java.util.Date;
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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ref_category_master")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefCategoryMaster {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categoryId;
	private String categoryCode;
	private String category;
	private String subCategory;
	private Date modifiedDate;
	private String modifiedBy;
	
	@OneToMany(mappedBy = "refCategoryMaster",cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<RefSourseDataFieldMapping> refSourseDataFieldMappings;
}
