package com.project.eventms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Category;

public interface  CategoryRepository  extends JpaRepository<Category, Integer> {
	Category 	findById(int id);

	Category findByName(String name);
 
}
