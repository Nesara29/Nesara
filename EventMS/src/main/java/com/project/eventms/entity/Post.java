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
@Table(name="post") 
public class Post   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id; 
 
	private String title;
	private String description; 
	private int location;
	private int status;
	private Date edate;
	private int category;
	private int subcategory;
	private int booking;
	private Timestamp creationtime;
	private Timestamp datepublished;
	 
}
