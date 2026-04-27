package com.project.eventms.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Booking;

public interface  BookingRepository  extends JpaRepository<Booking, Integer> {
	Booking	findById(int id);

	List<Booking> findByStatus(int acode, Sort descending);

	List<Booking> findByUser(int id);

	List<Booking> findByIncharge(int id);
 
}
