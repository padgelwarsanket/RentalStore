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
@Table(name="city")
public class City implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="city_id")
	private Short cityId;
	
	@ManyToOne
	@JoinColumn(name="country_id")
	private Country countryId;
	
	@Column(name="city")
	private String city;
	
	
	@Column(name="last_update")
	private LocalDateTime lastUpdate;

	

//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name="city_id")
//	private Short cityId;
//	
////	@ManyToOne
////	@JoinColumn(name="country_id", referencedColumnName = "country_id")
//	private Short countryId;
//	
////	@OneToMany(mappedBy="cityId")
////	List<Address> addresses = new ArrayList<>();
//	
//	@Column(name="city")
//	private String city;
//	
//	
//	@Column(name="last_update")
//	private Timestamp lastUpdate;
//	
	
	
}
