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
@Table(name="gallery") 
public class Gallery   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
    
	private String title;
	private String caption;
	private String description; 
	private String alternatetext; 
	private int relate;
 
}
