package com.project.eventms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Contactus;

public interface  ContactusRepository  extends JpaRepository<Contactus, Integer> {
	Contactus	findById(int id);

	Contactus findByName(String name);	
}
