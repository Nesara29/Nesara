package com.project.eventms.entity;
 
import java.sql.Date;
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
@Table(name="booking") 
public class Booking   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private int user; 
	private String title;
	private String details;
	private int category;
	private int subcategory; 
	private Date fromdate;
	private Date todate;
	private int incharge;
	private int status;
	private float price;
	private Timestamp creationtime;
	private Timestamp updationtime;
	 
	
}
