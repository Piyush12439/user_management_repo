package com.sapient.trg.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sapient.trg.onetoone.uni.Address;

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
@Table(name = "region_master")
public class RegionMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "region_id",nullable = false, columnDefinition = "NUMERIC(4)")
	private long regionID;
	
	@Column(length = 30)
	private String  name;
	
	@Column(length = 20)
	private String city;
	
	@Column(length = 20)
	private String state;
	
	@Column(length = 20)
	private String country;
	
//	@OneToMany(mappedBy = "region")
//    private List<StoreMaster> store;
	
	
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="taxid")
	TaxationMaster tax ;
		
	
	
}
