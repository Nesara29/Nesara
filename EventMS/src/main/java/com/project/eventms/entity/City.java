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
@Table(name="cities") 
public class City   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int state;

	private String name; 
	 
}