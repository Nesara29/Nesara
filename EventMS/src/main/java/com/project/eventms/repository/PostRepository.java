package com.project.eventms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Post; 

public interface  PostRepository  extends JpaRepository<Post, Integer> {
	Post	findById(int id);

	List<Post> findByStatus(int i);
 
}
