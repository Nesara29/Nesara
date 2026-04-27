package com.project.eventms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Client;

public interface  ClientRepository  extends JpaRepository<Client, Integer> {
	Client	findById(int id);
 
}
