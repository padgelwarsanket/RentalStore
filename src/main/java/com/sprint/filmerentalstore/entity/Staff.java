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
@Table(name="staff")
public class Staff implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Short staffId;
	
	@ManyToOne
    @JoinColumn(name = "store_id", referencedColumnName = "store_id")
    private Store storeId;
	
	@ManyToOne
    @JoinColumn(name = "address_id", referencedColumnName = "address_id")
    private Address addressId;

//	@OneToMany(mappedBy="managerStaffId")
//    private List<Store> stores;
	
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "picture")
    private String picture;

    @Column(name = "email")
    private String email;

    @Column(name = "active")
    private String active;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}
