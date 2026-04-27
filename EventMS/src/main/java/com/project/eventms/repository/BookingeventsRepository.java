package com.project.eventms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Bookingevents;

public interface  BookingeventsRepository  extends JpaRepository<Bookingevents, Integer> {
	Bookingevents	findById(int id);

	List<Bookingevents> findByBooking(int id);
 
}
