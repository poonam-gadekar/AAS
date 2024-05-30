package com.example.AAS.Entity;

import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
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
@Table(name = "ref_rule_set")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RefRuleSet {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "rule_set_id")
	private long ruleSetId;

	private String value;
	public String condition1;
	private boolean isDataSnapshot;
	private String modifiedBy;

	@OneToMany(mappedBy = "refRuleSet", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<TrnAttributeRuleSet> trnAttributeRuleSets;

	@ManyToOne
	@JoinColumn(name = "mapping_id")
	private RefSourseDataFieldMapping refSourseDataFieldMapping;
}
