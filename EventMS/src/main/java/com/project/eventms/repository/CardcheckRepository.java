package com.project.eventms.repository;

import com.project.eventms.entity.Cardcheck;
  
import org.springframework.data.jpa.repository.JpaRepository; 

public interface CardcheckRepository extends JpaRepository<Cardcheck, Long> {
	Cardcheck findById(long id);

	Cardcheck findByCardno (String name); 
}


 