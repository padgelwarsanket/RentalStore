package com.sprint.filmerentalstore.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="country")
public class Country implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="country_id")
	private Short countryId;
	
//	@OneToMany(mappedBy = "countryId")
//	private List<City> cities;
	
	@Column(name="country")
	private String country; 
	
	@Column(name="last_update")
	private LocalDateTime lastUpdate;

}
