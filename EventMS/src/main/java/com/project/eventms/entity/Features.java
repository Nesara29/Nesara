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
@Table(name="features") 
public class Features   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;  
	private String name;
	private String description;
}
