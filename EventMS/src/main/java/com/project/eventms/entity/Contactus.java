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
@Table(name="contactus") 
public class Contactus   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
	private String name;   
    private String email;   
  	private String mobile; 
  	private String message; 
    private Timestamp creationtime;
    private Timestamp updationtime; 
	 
}