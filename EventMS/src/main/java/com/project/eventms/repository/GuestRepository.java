package com.project.eventms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Guest;

public interface  GuestRepository  extends JpaRepository<Guest, Integer> {
	Guest	findById(int id);
 
}
