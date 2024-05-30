package com.example.AAS.Entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrnAttributeScreeningResults {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rowId;
	private Long batchDetailsId;
	private Long attributeId;
	private Long orgPermId;
	private String screeningResult;
	private Date reviewEffectiveDate;
	private Long currentStatusCode;
	private boolean isLatest;
	private boolean isFlagged;
	private Date modifiedDate;
	private String  modifiedBy;
	private Long fileBatchId;
	
	@ManyToOne
	@JoinColumn(name = "attribute_id1")
	private RefAttribute refAttribute;
	

}
