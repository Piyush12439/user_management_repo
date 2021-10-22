package com.sapient.trg.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "role_master") 
public class RoleMaster {
	@Id
	@Column(name = "role_id",nullable = false, columnDefinition = "NUMERIC(4)")
	private Long roleId;
	
	@Column(name = "role_name")
	private String name;
//	@OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
//    private List<UserProfile> userprofile;
	
	
	
}
