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
@Table(name="users") 
public class Users   {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String type;
	private String firstname;
	private String lastname;
	private String password;
	private String email; 
	private String mobile;
	private Timestamp creationtime;
	private Timestamp updationtime; 
	private int status;
	public String getName()
	{
		return this.firstname+" "+this.lastname;		
	}
}
