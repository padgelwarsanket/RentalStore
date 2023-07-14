package com.sprint.filmerentalstore.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="rental")
//@IdClass(RentalId.class)
public class Rental implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private Short rentalId;
	
//	@OneToMany(mappedBy="rentalId")
//	private List<Payment> payments;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staff_id", referencedColumnName = "staff_id")
    private Staff staffId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id" , referencedColumnName = "inventory_id")
    private Inventory inventoryId;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private Customer customerId;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @Column(name = "rental_date")
    private LocalDateTime rentalDate;


    @Column(name = "return_date")
    private LocalDate returnDate;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSSSS")
    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

}

//@Data
//class RentalId{
//	
//	private Staff staffId;
//	private Inventory inventoryId;
//	private Customer customerId;
//	
//}

