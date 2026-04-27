package com.project.eventms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Subcategories;

public interface  SubcategoriesRepository  extends JpaRepository<Subcategories, Integer> {
	Subcategories 	findById(int id);

	List<Subcategories> findByStatus(int i);

	List<Subcategories> findByCategoryAndStatus(int id,int status);
 
}
