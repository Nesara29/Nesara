package com.project.eventms.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.City; 

public interface  CitiesRepository  extends JpaRepository<City, Integer> {
	City	findById(int id);

	City findByName(String name);	

	List<City> findByState(int state);

	List<City> findByState(int state, Sort by);	
}
