package com.project.eventms.repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Bookingpayments;

public interface  BookingpaymentsRepository  extends JpaRepository<Bookingpayments, Integer> {
	Bookingpayments	findById(int id);

	List<Bookingpayments> findByBooking(int id);
	
	default double PaidForBooking(int id) {
		List<Bookingpayments> lst=this.findByBooking(id); 
		return lst.stream() 
		.mapToDouble(i -> i.getAmount()) 
        .sum(); 
	}

 
}
