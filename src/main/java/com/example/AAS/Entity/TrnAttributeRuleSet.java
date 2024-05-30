package com.example.AAS.Entity;

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
import lombok.ToString;

@Entity
@Table(name = "trn_attribute_rule_set")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TrnAttributeRuleSet {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rowId;

	private String criteria;
	private Long sequence;
	private String modifiedBy;

	@ManyToOne
	@JoinColumn(name = "attribute_id")
	private RefAttribute refAttribute;

	@ManyToOne
	@JoinColumn(name = "rule_set_id")
	private RefRuleSet refRuleSet;
}
