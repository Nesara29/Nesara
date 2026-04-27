package com.project.eventms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Gallery;

public interface  GalleryRepository  extends JpaRepository<Gallery, Integer> {
	Gallery	findById(int id);
 }
