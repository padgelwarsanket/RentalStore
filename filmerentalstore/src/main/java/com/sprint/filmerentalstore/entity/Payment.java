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
@Table(name = "payment")
public class Payment implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id")
	private Short paymentId;

	@ManyToOne
	@JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
	private Staff staffId;

	@ManyToOne
	@JoinColumn(name = "rental_id")
	private Rental rentalId;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customerId;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "payment_date")
	private LocalDateTime paymentDate;

	@Column(name = "last_update")
	private LocalDateTime lastUpdate;


}
