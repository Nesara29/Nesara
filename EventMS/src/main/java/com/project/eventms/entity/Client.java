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
@Table(name="client") 
public class Client   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int user; 
	private String address; 
	private int city;
	private int state; 
	private String pincode; 
	private Timestamp creationtime; 
	 
}
