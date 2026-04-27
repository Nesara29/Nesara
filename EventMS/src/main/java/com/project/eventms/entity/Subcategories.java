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
@Table(name="subcategories") 
public class Subcategories   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int category;
	private String name;
	private float price; 
	private int status;
	 
}
