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
@Table(name="store")
public class Store implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="store_id")
	private Short storeId;
	
//	@ManyToOne
//    @JoinColumn(name = "manager_staff_id", referencedColumnName = "manager_staff_id")
    private Short managerStaffId;
	
	@ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
	private Address addressId;
	
	//@OneToMany(mappedBy="storeId")
//	private List<Customer> customers;
	
//	@OneToMany(mappedBy="storeId")
//	private List<Inventory> inventoryId;
//	
//	@OneToMany(mappedBy="storeIdMany")
//    private List<Staff> staffIdOne;

	
	@Column(name="last_update")
	private LocalDateTime lastUpdate;

}
