package com.sprint.filmerentalstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="address")
public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id")
	private Short addressId;

//	@OneToMany(mappedBy = "addressId")
//	List<Long> customers = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "addressId")
//	List<Long> stores = new ArrayList<>();
//	
//	@OneToMany(mappedBy = "addressId")
//	List<Long> staffs = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="city_id",referencedColumnName = "city_id")
	private City cityId;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "addresss")
	private String address2;
	
	@Column(name = "district")
	private String district;

	
	@Column(name = "postal_code")
	private String postal_code;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "location")
	private String location;
	
	@Column(name = "last_update")
	private LocalDateTime lastUpdate;

}
