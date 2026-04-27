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
@Table(name="booking_events") 
public class Bookingevents   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int booking;
	private String title;
	private String venue ;
	private int location;
	private int  guests;
	private Timestamp start;
	private Timestamp end;
	private int status;
	private Timestamp creationtime; 
}
