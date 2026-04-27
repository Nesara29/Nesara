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
import java.sql.Date;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody; 

@Controller
public class HomeController { 
	
 
	 
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
    @Resource    private PostRepository           posts;
    @Resource    private StateRepository                 states; 
    @Resource    private BookingRepository           bookings;
    @Resource    private CategoryRepository     categories; 
    @Resource    private SubcategoriesRepository     subcategories; 
    @Resource    private SubcategoryfeaturesRepository    subcatfeatures;
    @Resource    private ContactusRepository contactus; 
    
	@PersistenceContext
    private EntityManager em; 
	@GetMapping("/")
    public String index(Model m){   
        return "redirect:/home";
    }  
	 @GetMapping("/home")
	    public String home(Model m){     
		    Hashtable<String,Object> arr= new Hashtable<String,Object>();
		    List<Hashtable<String,Object>> lst= new ArrayList<>();
		    for(Subcategories c:subcategories.findByStatus(1))
		    {
		    	arr= new Hashtable<String,Object>();
		    	arr.put("category",categories.findById(c.getCategory()).getName());
		    	arr.put("name",c.getName());
		    	arr.put("id", c.getId());
		    	arr.put("price",c.getPrice()); 
		    	arr.put("features",features.findAllById(subcatfeatures.getFeatures(c.getId())) );
		    	lst.add(arr);
		    }
		    
		    m.addAttribute("categories", lst);
		    lst= new ArrayList<>();
		    for(Post  c:posts.findByStatus(1))
		    {
		    	arr= new Hashtable<String,Object>();
		    	if(c.getCategory()==0)
		    	{
		    		arr.put("subcategory","NA");
			    	arr.put("category","NA");
		    	}
		    	else
		    	{
		    	arr.put("subcategory",subcategories.findById(c.getSubcategory()).getName());
		    	arr.put("category", categories.findById(c.getCategory()).getName());
		    	}
		    	arr.put("id", c.getId()); 
		    	arr.put("title", c.getTitle()); 
		    	arr.put("location",cities.findById(c.getLocation()).getName());  
		    	lst.add(arr);
		    }
		    m.addAttribute("posts",lst);
	        return "index";
	    }  

	    
	 @GetMapping("/home/pricing")
	    public String pricing(Model m){    
		    Hashtable<String,Object> arr= new Hashtable<String,Object>();
		    List<Hashtable<String,Object>> lst= new ArrayList<>();
		    for(Subcategories c:subcategories.findByStatus(1))
		    {
		    	arr= new Hashtable<String,Object>();
		    	arr.put("category",categories.findById(c.getCategory()).getName());
		    	arr.put("name",c.getName());
		    	arr.put("id", c.getId());
		    	arr.put("price",c.getPrice());
		    	arr.put("catid",c.getCategory());
		    	arr.put("features",features.findAllById(subcatfeatures.getFeatures(c.getId())) );
		    	lst.add(arr);
		    }
		    
		    m.addAttribute("services", categories.findAll()); 
		    m.addAttribute("categories", lst); 
	        return "pricing";
	    }  
	 
	 @GetMapping("/home/real-posts")
	    public String realweddinngs(Model m){    
		    Hashtable<String,Object> arr= new Hashtable<String,Object>();
		    List<Hashtable<String,Object>> lst= new ArrayList<>();
		    for(Post  c:posts.findByStatus(1))
		    {
		    	arr= new Hashtable<String,Object>();
		    	if(c.getCategory()==0)
		    	{
		    		arr.put("subcategory","NA");
			    	arr.put("category","NA");
		    	}
		    	else
		    	{
		    	arr.put("subcategory",subcategories.findById(c.getSubcategory()).getName());
		    	arr.put("category", categories.findById(c.getCategory()).getName());
		    	}
		    	arr.put("id", c.getId()); 
		    	arr.put("title", c.getTitle()); 
		    	arr.put("location",cities.findById(c.getLocation()).getName());  
		    	lst.add(arr);
		    }
		    m.addAttribute("posts",lst);
	        return "posts";
	    }  
	 @GetMapping("/home/gallery")
	    public String gallery(Model m){    
		 m.addAttribute("galleries",galleries.findAll(Sort.by("id").descending()));
	        return "gallery";
	    }  
	 
	 @GetMapping("/home/contact")
	    public String contact(Model m){     
	        return "contactus";
	    }  
	 @GetMapping("/home/about")
	    public String about(Model m){     
	        return "about";
	    }  
	 
	 @GetMapping("/home/packagedetail/{id}")
	    public String packagedetail(@PathVariable int id,Model m){     
		   Subcategories obj = subcategories.findById(id);
		    m.addAttribute("category", categories.findById(obj.getCategory())); 
		    m.addAttribute("subcategory", obj); 
		    m.addAttribute("features", features.findAllById(subcatfeatures.getFeatures(id)) );
	        return "packagedetails";
	    }  
	 
	 @GetMapping("/home/postdetails/{id}")
	    public String postdetails(@PathVariable int id,Model m){ 
		 
		 Hashtable<String,Object> post= new Hashtable<String,Object>(); 
		 Post c = posts.findById(id) ;  

		 if(c.getCategory()==0)
	    	{
			 post.put("subcategory","NA");
			 post.put("category","NA");
	    	}
	    	else
	    	{
	    		post.put("subcategory",subcategories.findById(c.getSubcategory()).getName());
	    		post.put("category", categories.findById(c.getCategory()).getName());
	    	}
		 post.put("id", c.getId()); 
		 post.put("title", c.getTitle()); 
		 post.put("location",cities.findById(c.getLocation()).getName());  
		 post.put("description", c.getDescription());    
		 
		    m.addAttribute("post", post);  
	        return "postdetails";
	    }  
	 
	 @PostMapping("/home/savecontactus") 
	    @ResponseBody
		   public Hashtable < String, Object > savecontactus( HttpServletRequest request,Model model)   {
		 Hashtable < String, Object > arr = new Hashtable < > ();  

		     String fullname = request.getParameter("name");       
		     String email =  request.getParameter("email");    
		     String mobile =  request.getParameter("mobile");    
		     String message =  request.getParameter("message");    
		      
		     List < String > errors = new ArrayList < String > ();  
		     
		     
		     
		     if (email.trim() .isBlank() || email == null)
			       errors.add("Email");
			     
		     if (  fullname == null || fullname.trim().isBlank()) errors.add("Full name");
		     if (  mobile == null || mobile.trim().isBlank()) errors.add("Mobile");
		     if (  message == null || message.trim().isBlank()) errors.add("Message");
		     
		     arr.put("success",  0); 
		     if (errors.size() == 0) {   
		       Contactus u=new Contactus(); 
		       u.setName(fullname); 
		       u.setEmail(email);  
		       u.setMobile(mobile);
		       u.setMessage(message);
		       u.setCreationtime(CommonFuns.ctm());
		       contactus.save(u); 
		       
		    	   arr.put("success",  1); 
			       arr.put("message",  " Submitted successfully!.");   
		     } else
		    	 arr.put("message",  "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));

		     return  arr;
		   }
	 
	
	
}