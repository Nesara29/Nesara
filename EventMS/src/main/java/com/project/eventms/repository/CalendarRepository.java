package com.project.eventms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Calendar;

public interface  CalendarRepository  extends JpaRepository<Calendar, Integer> {
	Calendar	findById(int id);
 
}
