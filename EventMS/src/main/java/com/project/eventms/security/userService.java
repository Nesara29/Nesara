package com.project.eventms.security;

import java.util.Hashtable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component; 
  
import com.project.eventms.entity.Users;
import com.project.eventms.service.LoginsService;
 

@Component
public class userService {
	@Autowired 
	private LoginsService memberService;  
 
	 
	public String getUserName()
	{
		Users user = memberService.findByUsername(
	  		      SecurityContextHolder.getContext().getAuthentication().getName()); 
		return user.getName();
	}
	
	public String getUserType()
	{
		Users user = memberService.findByUsername(
	  		      SecurityContextHolder.getContext().getAuthentication().getName()); 
		return user.getType();
	}
	   
}
