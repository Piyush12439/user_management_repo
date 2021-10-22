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
@Table(name = "store_master")
public class StoreMaster {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column( name = "store_id",nullable = false)
	private long storeID;
	
	private String  name;
	private String address;
	private Long mobileNo;
	private String email;
	private Long pincode;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="regionId")
	private RegionMaster region ;
//	@OneToMany(mappedBy = "store")
//    private List<UserProfile> userprofile;
	
	public StoreMaster(String name, String address, Long mobileNo, String email, Long pincode, RegionMaster regionID) {
		super();
		this.name = name;
		this.address = address;
		this.mobileNo = mobileNo;
		this.email = email;
		this.pincode = pincode;
		this.region = regionID;
	}
	
	
	
	
	
	
}
