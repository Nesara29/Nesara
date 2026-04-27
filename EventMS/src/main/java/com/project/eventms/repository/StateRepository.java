package com.project.eventms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.State;

public interface  StateRepository  extends JpaRepository<State, Integer> {
	State	findById(int id);

	State findByName(String name);	
}
