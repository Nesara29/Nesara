package com.project.eventms.entity;
 
import java.sql.Timestamp;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
 


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="booking_payments") 
public class Bookingpayments   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int booking;
	private float amount;
	private String paymentmode;
	private String transactionid ; 
	private Timestamp creationtime;  
}
