package com.project.eventms.controller;
 
 
import com.project.eventms.Bookingstatus;
import com.project.eventms.CommonFuns;
import com.project.eventms.Eventstatus;
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
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority; 
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
public class SubadminController { 
	
 
	 
	@Autowired 
	private LoginsService subadminService;  
	  
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
    @Resource    private BookingpaymentsRepository           bookpayments;
    @Resource    private BookingeventsRepository           bookevents;
    @Resource    private CategoryRepository     categories; 
    @Resource    private SubcategoriesRepository     subcategories; 
    @Resource    private SubcategoryfeaturesRepository    subcatfeatures;
    @Resource    ContactusRepository contactus;  
	  
	 @GetMapping("/subadmin/dashboard")
	    public String dashboard(HttpServletRequest request,Model m){       
		     
	        return "subadmin/dashboard";
	    }  

	 
	    @GetMapping("/subadmin/editprofile")
	    public String editprofile(Model model) {
	    	Users user = subadminService.findByUsername(
	  		      SecurityContextHolder.getContext().getAuthentication().getName());
	    	 
	    	model.addAttribute("status",user.getStatus());  
	    	model.addAttribute("firstname",user.getFirstname()); 
	    	model.addAttribute("lastname",user.getLastname()); 
	    	model.addAttribute("mobile", user.getMobile() ); 
	    	model.addAttribute("email", user.getEmail());  
	    	model.addAttribute("id",user.getId());   
	    	
	    	return "subadmin/myprofile";
	    } 
	    
	    @PostMapping("/subadmin/updateprofile") 
		   public String updateprofile( MultipartHttpServletRequest request,Model model) throws  Exception   {
		     Hashtable < String, Object > arr = new Hashtable < > ();  
		     
		     List < String > errors = new ArrayList < String > ();
		     
		     Users user = subadminService.findByUsername(
			      SecurityContextHolder.getContext().getAuthentication().getName());
		     
		     String firstname = request.getParameter("firstname");   
		     String lastname = request.getParameter("lastname");   
		     String mobile =  request.getParameter("mobile");       
			    MultipartFile img=request.getFile("profileimg");   
			    
		     if (  firstname == null || firstname.trim().isBlank()) errors.add("First name");
		     if (  lastname == null || lastname.trim().isBlank()) errors.add("Last name");
		      
		     Users obj;
			if (mobile.trim() .isBlank() || mobile == null)
		       errors.add("Mobile no");
		     else
		     {
		        obj = members.findByMobile(mobile);	   
		      if (obj != null && obj.getId()!=user.getId())
		         errors.add("Mobile no exists!");
		     } 
		      

		     if (errors.size() == 0) {  
		       obj =user; 
		       obj.setFirstname(firstname);
		       obj.setLastname(lastname);
		       obj.setMobile(mobile);
		       members.save(obj);
		       if(img.getSize()>0)
		        {
		    		 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimgs\\"; 
					(new File(fpath+user.getId())).createNewFile();
		             FileOutputStream fl;
		             fl=new FileOutputStream(fpath+user.getId()); 
		             fl.write(img.getBytes());                       
		             fl.close(); 
		        }
		       model.addAttribute("success",  " Updated successfully!."); 
		       
		     } else
		    	 model.addAttribute("error",  "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));

		     return  editprofile(model);
		   }
	     
	 
	 
	 @GetMapping("/subadmin/changepassword")
	    public String changepassword( Model m){       
	        return "subadmin/changepassword";
	    }  
	 
	 @PostMapping("/subadmin/updatepassword")
	    public String updatepassword(HttpServletRequest request,Model m)
	    {
	    	String password=request.getParameter("password");
	    	String newpassword=request.getParameter("newpassword");
	    	String confirmpassword=request.getParameter("confirmpassword");
	    	
	    	Users user = subadminService.findByUsername(
	    		      SecurityContextHolder.getContext().getAuthentication().getName());
	    		    if(newpassword.compareTo(confirmpassword)>0)
	    		    {
	    		    	m.addAttribute("updatepassword_errors", "New Password and Confirm Password Field do not match  !!");
	    		    }
	    		    else if (!subadminService.checkIfValidOldPassword(user, password)) {
	    		        m.addAttribute("updatepassword_errors", "Inavlid Current Password");
	    		    }
	    		    else if(!subadminService.validation_Password(newpassword))
	    		    {
	    		    	  m.addAttribute("updatepassword_errors", "Inavlid New Password.Please follow specified policies.");
	    		    }
	    		    else
	    		    {
	    		    	subadminService.changeUserPassword(user, newpassword);
	    		      m.addAttribute("updatepassword_success", "Password updated successfully");
	    		    }
	    	return changepassword(m);
	    } 
	 
	 
	 
	 @GetMapping("/subadmin/bookings")
	    public String bookings(Model m){      
		    List<Hashtable<String,Object>> lst = new ArrayList<>();

	    	 Users user =subadminService.findByUsername(
		  		      SecurityContextHolder.getContext().getAuthentication().getName());   
		    Hashtable<String, Object> arr = null ;
		    for(Booking  c:bookings.findByIncharge(user.getId()))
		    { 
		    	arr = new  Hashtable<String, Object>();
		    	arr.put("subcategory",subcategories.findById(c.getSubcategory()).getName());
		    	arr.put("category", categories.findById(c.getCategory()).getName()); 
		    	arr.put("id", c.getId()); 
		    	arr.put("title", c.getTitle()); 
		    	arr.put("status",Bookingstatus.getName(c.getStatus())); 
		    	arr.put("fromdate", c.getFromdate()); 
		    	arr.put("todate", c.getTodate()); 
		    	arr.put("price", c.getPrice());   
		    	arr.put("paid",bookpayments.PaidForBooking(c.getId())); 
		    	arr.put("user",members.findById(c.getUser()).getName());  
		    	arr.put("incharge","NA");
		    	if(c.getIncharge()>0) 
			    	arr.put("incharge",members.findById(c.getIncharge()).getName());
		    	arr.put("creationtime", c.getCreationtime());  
		    	lst.add(arr);
		    }
		    m.addAttribute("bookings",lst);
	        return "subadmin/bookings";
	    }
	 
	 
	 @GetMapping("/subadmin/viewbooking/{id}")
	    public String viewbooking(@PathVariable int id,Model m){     
		    Booking booking=bookings.findById(id);
		    Subcategories obj = subcategories.findById(booking.getSubcategory());
		    m.addAttribute("booking", booking);  
		    m.addAttribute("payments", bookpayments.findByBooking(id)); 
		    m.addAttribute("status", Bookingstatus.getName(booking.getStatus()));
		    m.addAttribute("paid",bookpayments.PaidForBooking(id)); 
		    if(booking.getIncharge()>0)
		       m.addAttribute("incharge", members.findById(booking.getIncharge())); 
		    m.addAttribute("category", categories.findById(obj.getCategory())); 
		    m.addAttribute("subcategory", obj); 
		    m.addAttribute("features", features.findAllById(subcatfeatures.getFeatures(id)) );

			   m.addAttribute("cities", cities.findAll(Sort.by("name").ascending()));

			   m.addAttribute("statuses", Eventstatus.values());
	        return "subadmin/viewbooking";
	    }  
	 
	 @PostMapping("/subadmin/events")
	 @ResponseBody
		public Hashtable<String,Object>  events(HttpServletRequest request)
		{
			Hashtable<String,Object> arr=new Hashtable<String,Object>();
			List<Hashtable<String,Object>> lst=new ArrayList<>(); 
			Hashtable<String,Object> arr1=new Hashtable<String,Object>();
			String action=request.getParameter("action");
			arr.put("success", 0);
			try {
			List<Bookingevents> cobj;
			int  booking,location,status,guests;
			String title,venue;
			Timestamp  start,end;
			int id;
			Bookingevents cobj1;
			switch(action)
			{ 
			case "readall":			
				booking=Integer.parseInt(request.getParameter("booking"));
				
				cobj= bookevents.findByBooking(booking);
				if(cobj.size()==0)
				{
					arr.put("success", 0);
					arr.put("message", "Not Exists!");
				}
				else
				{
					for(Bookingevents b:cobj)
					{
						arr1=new Hashtable<String,Object>();
						arr1.put("id", b.getId());
						arr1.put("title", b.getTitle());
						arr1.put("venue", b.getVenue());
						arr1.put("guests", b.getGuests());
						arr1.put("location",cities.findById(b.getLocation()).getName());
						arr1.put("time",CommonFuns.Formatdt(b.getStart())+" to "+CommonFuns.Formatdt(b.getEnd()));
						arr1.put("status",Eventstatus.getName( b.getStatus()));
						
						lst.add(arr1);
					}
					arr.put("success", 1);
				    arr.put("data",lst);
				}
				break;
			case "read":			
				id=Integer.parseInt(request.getParameter("id"));
				cobj1= bookevents.findById(id);
				if(cobj1==null)
				{
					arr.put("success", 0);
					arr.put("message", "Not Exists!");
				}
				else
				{
					arr1=new Hashtable<String,Object>();
					arr1.put("id", cobj1.getId());
					arr1.put("title", cobj1.getTitle());
					arr1.put("venue", cobj1.getVenue());
					arr1.put("location",cobj1.getLocation() );
					arr1.put("guests", cobj1.getGuests());
					arr1.put("start", cobj1.getStart().toString());
					arr1.put("end", cobj1.getEnd().toString());
					arr1.put("status",  cobj1.getStatus());
					arr.put("success", 1);
				   arr.put("data",arr1);
				}
				break;		
			    case "add":
			    	booking=Integer.parseInt(request.getParameter("booking"));
			    	location=Integer.parseInt(request.getParameter("city"));
			    	status=Integer.parseInt(request.getParameter("status")); 
			    	guests=Integer.parseInt(request.getParameter("guests")); 
			    	title= request.getParameter("title"); 
			    	venue= request.getParameter("venue");
			    	start=CommonFuns.toTimestamp(request.getParameter("start"));
			    	end=CommonFuns.toTimestamp(request.getParameter("end")); 
			    	 
			    		cobj1=new Bookingevents();
			    		cobj1.setBooking(booking);
			    		cobj1.setTitle(title);
			    		cobj1.setVenue(venue);
			    		cobj1.setLocation(location);
			    		cobj1.setGuests(guests);
			    		cobj1.setStart(start);
			    		cobj1.setEnd(end);
			    		cobj1.setStatus(status);
			    		
			    		bookevents.save(cobj1);
			    		arr.put("success", 1);
			    		arr.put("message", "Saved Successfully!");
			    		  
				break;
			    case "edit":
			    	id=Integer.parseInt(request.getParameter("id"));
			    	booking=Integer.parseInt(request.getParameter("booking"));
			    	location=Integer.parseInt(request.getParameter("city"));
			    	status=Integer.parseInt(request.getParameter("status")); 
			    	title= request.getParameter("title"); 
			    	venue= request.getParameter("venue");
			    	start=CommonFuns.toTimestamp(request.getParameter("start"));
			    	end=CommonFuns.toTimestamp(request.getParameter("end")); 
			    	guests=Integer.parseInt(request.getParameter("guests")); 
			    	 
			    	  cobj1= bookevents.findById(id);
					if(cobj1==null)
					{
						arr.put("success", 0);
						arr.put("message", "Not Exists!");
					}
					else
					{  
			    		cobj1=bookevents.findById(id);
			    		cobj1.setBooking(booking);
			    		cobj1.setTitle(title);
			    		cobj1.setVenue(venue);
			    		cobj1.setLocation(location);
			    		cobj1.setGuests(guests);
			    		cobj1.setStart(start);
			    		cobj1.setEnd(end);
			    		cobj1.setStatus(status);
			    		bookevents.save(cobj1);
			    		

			    		arr.put("success", 1);
			    		arr.put("message", "Saved Successfully!");
			    		}
			    	break;
			    case "delete":
			    	id=Integer.parseInt(request.getParameter("id"));
			    	cobj1= bookevents.findById(id);
					if(cobj1==null)
					{
						arr.put("success", 0);
						arr.put("message", "Not Exists!");
					}
					else
					{ 
						bookevents.delete(cobj1); 
						arr.put("message", "Deleted Successfully!");
					}
			    	break;
			}
			}
			catch(Exception e)
			{
				arr.put("message", e.getMessage());
			}
			return arr;
		}
	 
}