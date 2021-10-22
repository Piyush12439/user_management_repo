package com.sapient.trg.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "taxation_master")
public class TaxationMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "tax_id",nullable = false, columnDefinition = "NUMERIC(4)")
	private long taxId;
	
	@Column( name = "tax_code",nullable = false,columnDefinition = "NUMERIC(10)", unique = true)
	private long taxCode;
	
	@Column( name = "tax_name", length = 30)
	private String  taxName;
	
	@Column( name = "tax_percent", columnDefinition = "NUMERIC(4,2)")
	private Double taxPercent;
		
	@Column( name = "tax_effective_from")
	private LocalDate taxEffectiveFrom;
	
//	@OneToOne(mappedBy = "tax")
//    private RegionMaster region;
	
	

	
	
}
