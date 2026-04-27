package com.project.eventms.entity;
 
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
@Table(name="guest") 
public class Guest   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int booking;
	private String name;	  
	private int priority;
	private int outoftown; 
	private String tracksandgifts;
	private int city; 
}