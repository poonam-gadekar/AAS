package com.example.AAS.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "trn_entity_category_data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrnEntityCategoryData {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rowId;
	private Long batchDetailsId;
	private Long orgPermId;
	private Long soureEntityId;
	
	private String dataField;
	private String dataFieldValue;
	private String lastOverrideValue;
	private String lastOverrideComments;
	private boolean useOverride;
	private Date modifiedDate;
	private String modifiedBy;
	
	@ManyToOne
	@JoinColumn(name = "mappingId")
	private RefSourseDataFieldMapping refSourseDataFieldMapping;
	
	
	
}
