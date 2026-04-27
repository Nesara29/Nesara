package com.project.eventms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Features;

public interface  FeaturesRepository  extends JpaRepository<Features, Integer> {
	Features	findById(int id);

	Features findByName(String name);
 
}
