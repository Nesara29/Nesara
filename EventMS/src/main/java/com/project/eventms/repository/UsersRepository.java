package com.project.eventms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
 
import com.project.eventms.entity.Users;

public interface  UsersRepository  extends JpaRepository<Users, Integer> {
	Users	findById(int id);

	Users findByEmail(String un);

	int  countByType(String name);

	List<Users> findByType(String name);

	Users findByMobile(String mobile);

	List<Users>  findByTypeAndStatus(String name, int i); 
 
}
