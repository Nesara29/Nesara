package com.project.eventms.controller;
 
 
import com.project.eventms.CommonFuns;
import com.project.eventms.LoginTypes; 
import com.project.eventms.entity.*;
import com.project.eventms.repository.*;
import com.project.eventms.service.LoginsService;

import jakarta.annotation.Resource;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;  
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody; 

@Controller
public class ImageController { 
	
 
	 
	@Autowired 
	private LoginsService memberService;  
	  
    @Resource    private UsersRepository members;
    @Resource    private ClientRepository       clients; 
    @Resource    private CalendarRepository              calendar;
    @Resource    private CitiesRepository                cities;
    @Resource    private BookingpaymentsRepository               bpayments;
    @Resource    private BookingeventsRepository                bevents;
    @Resource    private FeaturesRepository              features;
    @Resource    private GalleryRepository               galleries;
    @Resource    private GuestRepository                 guests; 
    @Resource    private PostRepository               posts;
    @Resource    private StateRepository                 states; 
    @Resource    private BookingRepository           bookings;
    @Resource    private CategoryRepository     categories; 
    @Resource    private SubcategoriesRepository     subcategories; 
    
	 
	 
	  final String dimg = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\noimg.png"; 
	  	 
	    @GetMapping("/home/viewpackageimg/{id}")
	    @ResponseBody
	    public ResponseEntity<InputStreamResource> viewpackageimg(@PathVariable int id) 
	    	{ 
	    	try {
	    	 InputStreamResource file;
	  	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\subcategories\\"; 
	  	 
	  	   if(new File(fpath+id).exists())
	  	        file=  new InputStreamResource(new FileInputStream(new File(fpath+id)));
		else
		
				file=  new InputStreamResource(new FileInputStream(new File(dimg)));
			
	  	   
	  	   return ResponseEntity.ok()
	  			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
	  			   .body(file); 
	    	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	    	}
	 
	    @GetMapping("/home/viewpostimg/{id}")
	    @ResponseBody
	    public ResponseEntity<InputStreamResource> viewpostweddingimg(@PathVariable int id) throws IOException 
	    	{ 
	    	try {
	    	 InputStreamResource file;
	  	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\posts\\"; 
	  	 
	  	  
	  	   
	  	    
	  	   if(new File(fpath+id).exists())
	  	        file=  new InputStreamResource(new FileInputStream(new File(fpath+id)));
		else {
			    	  	file=  new InputStreamResource(new FileInputStream(new File(dimg)));
		}
		
				
			
	  	   
	  	   return ResponseEntity.ok()
	  			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
	  			   .body(file); 
	    	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	    	}
	    
	    

	    @GetMapping("/home/viewgalleryimg/{id}")
	    @ResponseBody
	    public ResponseEntity<InputStreamResource> viewgalleryimg(@PathVariable int id) throws IOException 
	    	{ 
	    	try {
	    	 InputStreamResource file;
	  	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\galleries\\"; 
	  	 
	  	  
	  	   
	  	    
	  	   if(new File(fpath+id).exists())
	  	        file=  new InputStreamResource(new FileInputStream(new File(fpath+id)));
		else {
			 
			    	  	file=  new InputStreamResource(new FileInputStream(new File(dimg)));
		}
		
				
			
	  	   
	  	   return ResponseEntity.ok()
	  			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
	  			   .body(file); 
	    	} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
	    	}
	    
	    
		   @GetMapping("/home/myprofileimg")
		    @ResponseBody
		    public ResponseEntity<InputStreamResource> myprofileimg(HttpServletResponse response) throws Exception
		    	{ 
			   Users user = memberService.findByUsername(
			  		      SecurityContextHolder.getContext().getAuthentication().getName());   
		    	  
		    	  InputStreamResource file;
		    	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimgs\\"; 
		    	   file=  new InputStreamResource(new FileInputStream(new File(dimg)));
		    	  //System.out.println(fpath+user.getId());
		    	   if(new File(fpath+user.getId()).exists())
		    	        file=  new InputStreamResource(new FileInputStream(new File(fpath+user.getId()))); 
		    	   
		    	   return ResponseEntity.ok()
		    			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
		    			   .body(file); 
		    	} 
		   

		   @GetMapping("/home/viewprofileimg/{id}")
		    @ResponseBody
		    public ResponseEntity<InputStreamResource> viewprofileimg(@PathVariable int id,HttpServletResponse response) throws Exception
		    	{ 
			   
		    	  
		    	  InputStreamResource file;
		    	   String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimgs\\"; 
		    	   file=  new InputStreamResource(new FileInputStream(new File(dimg)));
		  
		    	   if(new File(fpath+id).exists())
		    	        file=  new InputStreamResource(new FileInputStream(new File(fpath+id))); 
		    	   
		    	   return ResponseEntity.ok()
		    			   .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;")
		    			   .body(file); 
		    	} 
	  
}