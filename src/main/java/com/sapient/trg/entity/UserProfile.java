package com.sapient.trg.entity;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "user_profile")
public class UserProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String user_name;
	
	//@JsonDeserialize(using = LocalDateDeserializer.class)  
	//@JsonSerialize(using = LocalDateSerializer.class) 
	@Column( name = "hiredate")
	private LocalDate hiredate;
	
	private String email;
	private Long mobile;
	private String address;
	private String city;
	private String state;
	private Long zipcode;
	private String country;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="userId")
	private UserCredential credential ;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="roleId")
	private RoleMaster role ;
	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	@JoinColumn(name="storeId")
	private StoreMaster store ;
	
	
	
}
