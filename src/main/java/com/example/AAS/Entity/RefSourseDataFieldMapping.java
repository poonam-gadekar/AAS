package com.example.AAS.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ref_source_data_field_mapping")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefSourseDataFieldMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mappingId;
	private String fileTypeId;
	private Long categoryId;
	private String dataField;
	private Date validFrom;
	private Date validTo;
	private String modifiedBy;
	
	@ManyToOne
	@JoinColumn(name = "refCategoryId")
	private RefCategoryMaster refCategoryMaster;
	
	
	@OneToMany(mappedBy = "refSourseDataFieldMapping", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<RefRuleSet> refRuleSets;
	
	@OneToMany(mappedBy = "refSourseDataFieldMapping")
	private List<TrnEntityCategoryData> trnEntityCategoryDatas;
	
	@ManyToOne
	@JoinColumn(name = "refSourseFileTypeId")
	private RefSourseFileType  refSourseFileType;
	
}
