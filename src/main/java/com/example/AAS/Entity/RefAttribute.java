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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "ref_attribute")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class RefAttribute {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long attributeId;
	private String attributeCode;
	private String description;
	private String attributeGroup;
	private LocalDateTime modifiedDate;
	private String modifiedBy;
	private Long rationaleMappingId;

	@OneToMany(mappedBy = "refAttribute", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<TrnAttributeRuleSet> trnAttributeRuleSets;
	
	@OneToMany(mappedBy = "refAttribute", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<TrnAttributeScreeningResults> trnAttributeScreeningResults;
	
	

}
