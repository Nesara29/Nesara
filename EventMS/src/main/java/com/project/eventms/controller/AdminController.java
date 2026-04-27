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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
import java.util.List;
import java.util.Optional;
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
public class AdminController { 
	
 
	 
	@Autowired 
	private LoginsService adminService;  
	  
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
    @Resource    ContactusRepository contactus; 
    @Resource    private BookingpaymentsRepository           bookpayments;
    @Resource    private BookingeventsRepository           bookevents;
    
	 @GetMapping("/home/admin-login")
	    public String adminlogin(Model m){     
		    //adminService.changeUserPassword(members.findById(1), "admin@123");
	        return "admin/login";
	    }  
	 
	 
	 @PostMapping("/home/uloginauth")
	   public String uloginauth(HttpServletRequest request)
	   {
		   	 String userName=request.getParameter("username");
	    	 String password=request.getParameter("password");   
	    	 Users userDetails = adminService.findByUsername(userName); 
	    	 boolean error=false;
	    	 if(userDetails!=null)
	         if(adminService.checkIfValidOldPassword(userDetails, password) && userDetails.getStatus()==1)
	         {  
				  	  	 	
	              Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getEmail (),userDetails.getPassword (),adminService.getAuthorities(userDetails.getType()));
	               
	              SecurityContextHolder.getContext().setAuthentication(auth);
	              request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
	              return "redirect:/"+userDetails.getType().toLowerCase()+"/dashboard"; 
	         }
	         else
	            error=true;
	       if(error)
	       { 
	    		   return "redirect:/home/admin-login?error=1";  
	       }
	       return "redirect:/home/admin-login?error=1"; 
	   }
	 
	 
	 @GetMapping("/admin/dashboard")
	    public String dashboard(Model m){     
		    m.addAttribute("user_count", members.countByType(LoginTypes.CLIENT.name()));  
		    m.addAttribute("booking_count", bookings.count());
		    m.addAttribute("gallery_count", galleries.count());
		    m.addAttribute("event_post", posts.count());
		    
	        return "admin/dashboard";
	    }  
	 
	 @GetMapping("/admin/blogevents")
	    public String blogevents(Model m){      
		    List<Hashtable<String,Object>> lst = new ArrayList<>();
		    Hashtable<String, Object> arr ;
		    for(Post  c:posts.findAll())
		    {
		    	arr = new Hashtable<String,Object>();
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
		    	arr.put("status", c.getStatus()); 
		    	arr.put("edate", c.getEdate()); 
		    	arr.put("description", c.getDescription()); 
		    	arr.put("location",cities.findById(c.getLocation()).getName());  
		    	arr.put("creationtime", c.getCreationtime()); 
		    	arr.put("datepublished", c.getDatepublished()); 
		    	lst.add(arr);
		    }
		    m.addAttribute("posts",lst);
	        return "admin/blogevents";
	    } 
	 
	 @GetMapping("/admin/blogeventsadd")
	    public String blogeventsadd(Model m){       
		   
			 List<Hashtable<String, Object>> lst = new ArrayList<>();
			 
				for(Subcategories c:subcategories.findAll())
				    {
				    	Hashtable<String, Object> arr = new Hashtable<String,Object>();
				    	arr.put("category",categories.findById(c.getCategory()).getName());
				    	arr.put("name",c.getName());
				    	arr.put("id", c.getId()); 
				    	 
				    	lst.add(arr);
				    }
				    
				    m.addAttribute("packages", lst);
		   
		   m.addAttribute("cities", cities.findAll(Sort.by("name").ascending()));
			 m.addAttribute("bookings", bookings.findByStatus(Bookingstatus.Completed.acode(), Sort.by("id").descending()));
		   return "admin/blogeventsadd";
	    } 
	 
	 @GetMapping("/admin/blogeventsedit/{id}")
	    public String blogeventsedit(@PathVariable int id,Model m){       
		   
			 List<Hashtable<String, Object>> lst = new ArrayList<>();
			 
				for(Subcategories c:subcategories.findAll())
				    {
				    	Hashtable<String, Object> arr = new Hashtable<String,Object>();
				    	arr.put("category",categories.findById(c.getCategory()).getName());
				    	arr.put("name",c.getName());
				    	arr.put("id", c.getId()); 
				    	 
				    	lst.add(arr);
				    }
				    
				    m.addAttribute("packages", lst);
		   
		   m.addAttribute("cities", cities.findAll(Sort.by("name").ascending()));
			 m.addAttribute("bookings", bookings.findByStatus(Bookingstatus.Completed.acode(), Sort.by("id").descending()));
			  m.addAttribute("post", posts.findById(id));
			 return "admin/blogeventsedit";
	    } 
 
	 
	 @PostMapping("/admin/posts")
	 @ResponseBody
	 	public Hashtable<String,Object>  posts(MultipartHttpServletRequest  request) 
	 	{
	 		Hashtable<String,Object> arr=new Hashtable<String,Object>();
	 		try {
	 		String action=request.getParameter("action");
	 		arr.put("success", 0);
	 		Post cobj;
	 		String title,desc; 
	 		int id,booking,location,pkge,status;
	 		Date edate;
	 		MultipartFile img ; 
	 		switch(action)
	 		{
	 		case "readall":
	 			arr.put("success", 1);
	 			arr.put("data", posts.findAll());
	 			break;
	 		case "read":			
	 			 id=Integer.parseInt(request.getParameter("id"));
	 			cobj= posts.findById(id);
	 			if(cobj==null)
	 			{
	 				arr.put("success", 0);
	 				arr.put("message", "Not Exists!");
	 			}
	 			else
	 			{
	 				arr.put("success", 1);
	 			   arr.put("data",cobj);
	 			}
	 			break;
	 		    case "add":
	 		    	title=request.getParameter("title");  
	 		    	desc=request.getParameter("description");  
	 		    	pkge=CommonFuns.cint(request.getParameter("package"));
	 		    	location=CommonFuns.cint(request.getParameter("city"));
	 		    	booking=CommonFuns.cint(request.getParameter("booking"));
	 		    	edate=Date.valueOf(request.getParameter("edate"));
	 		    	status=CommonFuns.cint(request.getParameter("status"));
	 		    	
	 		    	img=request.getFile("img"); 
	 		    		cobj=new Post();
	 		    		cobj.setTitle(title);  
	 		    		if(pkge==0)
	 		    			cobj.setCategory( 0);
	 		    		else
	 		    		cobj.setCategory( subcategories.findById(pkge).getCategory());
	 		    		cobj.setDescription(desc);
	 		    		cobj.setEdate(edate);
	 		    		cobj.setStatus(status);
	 		    		cobj.setLocation(location);
	 		    		cobj.setBooking(booking);
	 		    		cobj.setSubcategory(pkge); 
	 		    		posts.save(cobj);
	 		    		if(img.getSize()>0)
	 		    		{
	 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\posts\\"; 
	 						(new File(fpath+cobj.getId())).createNewFile();
	 			             FileOutputStream fl;
	 			             fl=new FileOutputStream(fpath+cobj.getId()); 
	 			             fl.write(img.getBytes());                       
	 			             fl.close(); 
	 		    		}
	 		    		 
	 		    		arr.put("success", 1);
	 		    		arr.put("message", "Saved Successfully!"); 
	 		    	 
	 			break;
	 		    case "edit":
	 		    	id=Integer.parseInt(request.getParameter("id"));
	 		    	title=request.getParameter("title");  
	 		    	desc=request.getParameter("description");  
	 		    	pkge=CommonFuns.cint(request.getParameter("package"));
	 		    	location=CommonFuns.cint(request.getParameter("city"));
	 		    	booking=CommonFuns.cint(request.getParameter("booking"));
	 		    	edate=Date.valueOf(request.getParameter("edate"));
	 		    	status=CommonFuns.cint(request.getParameter("status"));
	 		    	
	 		    	img=request.getFile("img"); 
	 		    	cobj= posts.findById(id);
	 				if(cobj==null)
	 				{
	 					arr.put("success", 0);
	 					arr.put("message", "Not Exists!");
	 				}
	 				else
	 				{ 
	 					 
	 						cobj= posts.findById(id);
	 						cobj.setTitle(title);  
	 						if(pkge==0)
		 		    			cobj.setCategory( 0);
		 		    		else
		 		    		cobj.setCategory( subcategories.findById(pkge).getCategory());
		 		    		cobj.setDescription(desc);
		 		    		cobj.setEdate(edate);
		 		    		cobj.setStatus(status);
		 		    		cobj.setLocation(location);
		 		    		cobj.setSubcategory(pkge); 
		 		    		cobj.setBooking(booking);
		 		    		posts.save(cobj);
		 		    		if(img.getSize()>0)
		 		    		{
		 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\posts\\"; 
		 						(new File(fpath+cobj.getId())).createNewFile();
		 			             FileOutputStream fl;
		 			             fl=new FileOutputStream(fpath+cobj.getId()); 
		 			             fl.write(img.getBytes());                       
		 			             fl.close(); 
		 		    		}
		 		    		 
	 					arr.put("success", 1);
	 					arr.put("message", "Saved Successfully!");
	 					 
	 			    	}  
	 		    	break;
	 		     
	 		}
	 		}
	 		catch(IOException e)
	 		{

				arr.put("success", 0);
				arr.put("message", e.getMessage());
	 		}
	 		catch(Exception e)
	 		{

					arr.put("success", 0);
					arr.put("message", e.getMessage());
	 		}
	 		return arr;
	 	}
	 
	 
	 @GetMapping("/admin/client")
	    public String clients(Model m){      
		    m.addAttribute("clients", members.findByType(LoginTypes.CLIENT.name()));
	        return "admin/client";
	    } 
	 
	 
	 @GetMapping("/admin/features")
	    public String features(Model m){      
		 
	        return "admin/features";
	    } 
	 
	 @GetMapping("/admin/categories")
	    public String categories(Model m){       
	        return "admin/categories";
	    } 
	  
	 @GetMapping("/admin/packages")
	    public String packages(Model m){       
		 
		 List<Hashtable<String, Object>> lst = new ArrayList<>();
		 
		for(Subcategories c:subcategories.findAll())
		    {
		    	Hashtable<String, Object> arr = new Hashtable<String,Object>();
		    	arr.put("category",categories.findById(c.getCategory()).getName());
		    	arr.put("name",c.getName());
		    	arr.put("id", c.getId());
		    	arr.put("status", c.getStatus() );
		    	arr.put("price",c.getPrice()); 
		    	 
		    	lst.add(arr);
		    }
		    
		    m.addAttribute("subcategories", lst);
		 
	        return "admin/packages";
	    } 
	  
	 @GetMapping("/admin/photosadd")
	    public String photosadd(Model m){      
		 m.addAttribute("bookings", bookings.findByStatus(Bookingstatus.Completed.acode(), Sort.by("id").descending()));
	        return "admin/photosadd";
	    } 
	 @GetMapping("/admin/photosedit/{id}")
	    public String photosedit(@PathVariable int id,Model m){       
			m.addAttribute("photo", galleries.findById(id));   
			 m.addAttribute("bookings", bookings.findByStatus(Bookingstatus.Completed.acode(), Sort.by("id").descending()));
	        return "admin/photosedit";
	    } 
	 
	 @GetMapping("/admin/photosview")
	    public String photosview(Model m){      
		 m.addAttribute("galleries",galleries.findAll(Sort.by("id").descending()));
	        return "admin/photosview";
	    } 

	 
	 @GetMapping("/admin/users")
	    public String users(Model m){  
		 
		 List<Users> objs = members.findByType(LoginTypes.ADMIN.name());
		 objs.addAll(members.findByType(LoginTypes.SUBADMIN.name()));
		 
		  m.addAttribute("users",objs); 
	        return "admin/users";
	    } 

	 
	 @GetMapping("/admin/taskallcalendar")
	    public String taskallcalendar(Model m){  
		   
	        return "admin/taskallcalendar";
	    } 
	 
	 @PostMapping("/admin/features")
	 @ResponseBody
	 	public Hashtable<String,Object>  features(HttpServletRequest request)
	 	{
	 		Hashtable<String,Object> arr=new Hashtable<String,Object>();
	 		String action=request.getParameter("action");
	 		arr.put("success", 0);
	 		Features cobj;
	 		String name,desc;
	 		int id;
	 		switch(action)
	 		{
	 		case "readall":
	 			arr.put("success", 1);
	 			arr.put("data", features.findAll());
	 			break;
	 		case "read":			
	 			 id=Integer.parseInt(request.getParameter("id"));
	 			cobj= features.findById(id);
	 			if(cobj==null)
	 			{
	 				arr.put("success", 0);
	 				arr.put("message", "Not Exists!");
	 			}
	 			else
	 			{
	 				arr.put("success", 1);
	 			   arr.put("data",cobj);
	 			}
	 			break;
	 		    case "add":
	 		    	name=request.getParameter("name");
	 		    	desc=request.getParameter("desc");
	 		    	if(name!="")
	 		    	{
	 		    		if(features.findByName(name)==null)
	 		    		{
	 		    		cobj=new Features();
	 		    		cobj.setName(name);
	 		    		cobj.setDescription(desc);
	 		    		features.save(cobj);
	 		    		arr.put("success", 1);
	 		    		arr.put("message", "Saved Successfully!");
	 		    		}
	 		    		else
	 		    			arr.put("message", "Name already Exists!"); 
	 		    	}
	 		    	else
	 		    	  arr.put("message", "Name required!");
	 			break;
	 		    case "edit":
	 		    	id=Integer.parseInt(request.getParameter("id"));
	 		    	  name=request.getParameter("name");
	 		    	  desc=request.getParameter("desc");
	 		    	  cobj= features.findById(id);
	 				if(cobj==null)
	 				{
	 					arr.put("success", 0);
	 					arr.put("message", "Not Exists!");
	 				}
	 				else
	 				{
	 					if(name!="")
	 			    	{
	 					cobj=features.findByName(name);
	 					if(cobj==null || cobj.getId()==id)
	 					{
	 						cobj= features.findById(id);
	 					cobj.setName(name); ;
	 					cobj.setDescription(desc);
	 					features.save(cobj);
	 					arr.put("success", 1);
	 					arr.put("message", "Saved Successfully!");
	 					}
	 					else
	 						arr.put("message", "Name already Exists!");
	 			    	}
	 					else
	 				    	  arr.put("message", "Name required!");
	 				}
	 		    	break;
	 		    case "delete":
	 		    	id=Integer.parseInt(request.getParameter("id"));
	 		    	cobj= features.findById(id);
	 				if(cobj==null)
	 				{
	 					arr.put("success", 0);
	 					arr.put("message", "Not Exists!");
	 				}
	 				else
	 				{ 
	 					features.delete(cobj); 
	 					arr.put("message", "Deleted Successfully!");
	 				}
	 		    	break;
	 		}
	 		return arr;
	 	}
	 
	 @PostMapping("/admin/category")
	 @ResponseBody
		public Hashtable<String,Object>  categories(HttpServletRequest request)
		{
			Hashtable<String,Object> arr=new Hashtable<String,Object>();
			String action=request.getParameter("action");
			arr.put("success", 0);
			Category cobj;
			String name;
			int id;
			switch(action)
			{
			case "readall":
				arr.put("success", 1);
				arr.put("data", categories.findAll());
				break;
			case "read":			
				 id=Integer.parseInt(request.getParameter("id"));
				cobj= categories.findById(id);
				if(cobj==null)
				{
					arr.put("success", 0);
					arr.put("message", "Not Exists!");
				}
				else
				{
					arr.put("success", 1);
				   arr.put("data",cobj);
				}
				break;
			    case "add":
			    	name=request.getParameter("name"); 
			    	if(name!="")
			    	{
			    		if(categories.findByName(name)==null)
			    		{
			    		cobj=new Category();
			    		cobj.setName(name); 
			    		categories.save(cobj);
			    		arr.put("success", 1);
			    		arr.put("message", "Saved Successfully!");
			    		}
			    		else
			    			arr.put("message", "Name already Exists!"); 
			    	}
			    	else
			    	  arr.put("message", "Name required!");
				break;
			    case "edit":
			    	id=Integer.parseInt(request.getParameter("id"));
			    	  name=request.getParameter("name"); 
			    	  cobj= categories.findById(id);
					if(cobj==null)
					{
						arr.put("success", 0);
						arr.put("message", "Not Exists!");
					}
					else
					{
						if(name!="")
				    	{
						cobj=categories.findByName(name);
						if(cobj==null || cobj.getId()==id)
						{
							cobj= categories.findById(id);
						cobj.setName(name); ; 
						categories.save(cobj);
						arr.put("success", 1);
						arr.put("message", "Saved Successfully!");
						}
						else
							arr.put("message", "Name already Exists!");
				    	}
						else
					    	  arr.put("message", "Name required!");
					}
			    	break;
			    case "delete":
			    	id=Integer.parseInt(request.getParameter("id"));
			    	cobj= categories.findById(id);
					if(cobj==null)
					{
						arr.put("success", 0);
						arr.put("message", "Not Exists!");
					}
					else
					{ 
						categories.delete(cobj); 
						arr.put("message", "Deleted Successfully!");
					}
			    	break;
			}
			return arr;
		}
	 
	 @GetMapping("/admin/packageadd")
	    public String packageadd(Model m){       
			m.addAttribute("categories", categories.findAll());
			m.addAttribute("features", features.findAll());
	        return "admin/packageadd";
	    } 
	 @GetMapping("/admin/packageedit/{id}")
	    public String packageedit(@PathVariable int id,Model m){       
			m.addAttribute("package", subcategories.findById(id));  
			m.addAttribute("packfeatures", subcatfeatures.getFeatures(id)); 
			m.addAttribute("categories", categories.findAll());
			m.addAttribute("features", features.findAll());
	        return "admin/packageedit";
	    } 
	 
	 
	 
	 @PostMapping("/admin/subcategories")
	 @ResponseBody
	 	public Hashtable<String,Object>  subcategories(MultipartHttpServletRequest  request) throws IOException
	 	{
	 		Hashtable<String,Object> arr=new Hashtable<String,Object>();
	 		String action=request.getParameter("action");
	 		arr.put("success", 0);
	 		Subcategories cobj;
	 		String name ;
	 		float price;
	 		int category;
	 		MultipartFile img ;
	 		int id;
	 		int status;
	 		switch(action)
	 		{
	 		case "readall":
	 			arr.put("success", 1);
	 			arr.put("data", subcategories.findAll());
	 			break;
	 		case "read":			
	 			 id=Integer.parseInt(request.getParameter("id"));
	 			cobj= subcategories.findById(id);
	 			if(cobj==null)
	 			{
	 				arr.put("success", 0);
	 				arr.put("message", "Not Exists!");
	 			}
	 			else
	 			{
	 				arr.put("success", 1);
	 			   arr.put("data",cobj);
	 			}
	 			break;
	 		    case "add":
	 		    	name=request.getParameter("title"); 
	 		    	price=CommonFuns.cfloat(request.getParameter("price"));
	 		    	category=CommonFuns.cint(request.getParameter("category"));
	 		    	img=request.getFile("img");
	 		    	if(name!="")
	 		    	{ 
	 		    		cobj=new Subcategories();
	 		    		cobj.setName(name); 
	 		    		cobj.setCategory(category);
	 		    		cobj.setPrice(price);
	 		    		cobj.setStatus(1);
	 		    		subcategories.save(cobj);
	 		    		if(img.getSize()>0)
	 		    		{
	 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\subcategories\\"; 
	 						(new File(fpath+cobj.getId())).createNewFile();
	 			             FileOutputStream fl;
	 			             fl=new FileOutputStream(fpath+cobj.getId()); 
	 			             fl.write(img.getBytes());                       
	 			             fl.close(); 
	 		    		}
	 		    		for(String s:request.getParameterValues("features[]"))
	 		    		{
	 		    			Subcategoryfeatures fobj = new Subcategoryfeatures();
	 		    			fobj.setFeature(CommonFuns.cint(s));
	 		    			fobj.setSubcategory(cobj.getId()); 
	 		    			subcatfeatures.save(fobj);
	 		    		}

	 		    		arr.put("success", 1);
	 		    		arr.put("message", "Saved Successfully!");
	 		    	}
	 		    	else
	 		    	  arr.put("message", "Name required!");
	 			break;
	 		    case "edit":
	 		    	id=Integer.parseInt(request.getParameter("id"));
	 		    	  name=request.getParameter("title"); 
		 		    	price=CommonFuns.cfloat(request.getParameter("price"));
		 		    	category=CommonFuns.cint(request.getParameter("category"));
		 		    	img=request.getFile("img"); 
		 		    	status=CommonFuns.cint(request.getParameter("status"));
	 		    	  cobj= subcategories.findById(id);
	 				if(cobj==null)
	 				{
	 					arr.put("success", 0);
	 					arr.put("message", "Not Exists!");
	 				}
	 				else
	 				{
	 					if(name!="")
	 			    	{
	 					 
	 						cobj= subcategories.findById(id);
	 						cobj.setName(name); 
		 		    		cobj.setCategory(category);
		 		    		cobj.setPrice(price);
		 		    		cobj.setStatus(status);
		 		    		subcategories.save(cobj);
		 		    		if(img.getSize()>0)
		 		    		{
		 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\subcategories\\"; 
		 						(new File(fpath+cobj.getId())).createNewFile();
		 			             FileOutputStream fl;
		 			             fl=new FileOutputStream(fpath+cobj.getId()); 
		 			             fl.write(img.getBytes());                       
		 			             fl.close(); 
		 		    		}
		 		    		subcatfeatures.deleteAll(subcatfeatures.findBySubcategory(id));
		 		    		for(String s:request.getParameterValues("features[]"))
		 		    		{
		 		    			Subcategoryfeatures fobj = new Subcategoryfeatures();
		 		    			fobj.setFeature(CommonFuns.cint(s));
		 		    			fobj.setSubcategory(cobj.getId()); 
		 		    			subcatfeatures.save(fobj);
		 		    		}
	 					arr.put("success", 1);
	 					arr.put("message", "Saved Successfully!");
	 					 
	 			    	}
	 					else
	 				    	  arr.put("message", "Name required!");
	 				}
	 		    	break;
	 		     
	 		}
	 		return arr;
	 	}
	 
	 
 
	 
	 @PostMapping("/admin/galleries")
	 @ResponseBody
	 	public Hashtable<String,Object>  galleries(MultipartHttpServletRequest  request) throws IOException
	 	{
	 		Hashtable<String,Object> arr=new Hashtable<String,Object>();
	 		String action=request.getParameter("action");
	 		arr.put("success", 0);
	 		Gallery cobj;
	 		String title,caption,alt,desc ; 
	 		int relate;
	 		MultipartFile img ;
	 		int id; 
	 		switch(action)
	 		{
	 		case "readall":
	 			arr.put("success", 1);
	 			arr.put("data", galleries.findAll());
	 			break;
	 		case "read":			
	 			 id=Integer.parseInt(request.getParameter("id"));
	 			cobj= galleries.findById(id);
	 			if(cobj==null)
	 			{
	 				arr.put("success", 0);
	 				arr.put("message", "Not Exists!");
	 			}
	 			else
	 			{
	 				arr.put("success", 1);
	 			   arr.put("data",cobj);
	 			}
	 			break;
	 		    case "add":
	 		    	title=request.getParameter("title");  
	 		    	relate=CommonFuns.cint(request.getParameter("booking")); 
	 		    	caption=request.getParameter("caption");  
	 		    	alt=request.getParameter("alternatetext");  
	 		    	desc=request.getParameter("description"); 
	 		    	img=request.getFile("img");
	 		    	if(title!="")
	 		    	{ 
	 		    		cobj=new Gallery();
	 		    		cobj.setTitle(title); 
	 		    		cobj.setRelate(relate);  
	 		    		cobj.setAlternatetext(alt);
	 		    		cobj.setDescription(desc);
	 		    		cobj.setCaption(caption);
	 		    		galleries.save(cobj);
	 		    		if(img.getSize()>0)
	 		    		{
	 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\galleries\\"; 
	 						(new File(fpath+cobj.getId())).createNewFile();
	 			             FileOutputStream fl;
	 			             fl=new FileOutputStream(fpath+cobj.getId()); 
	 			             fl.write(img.getBytes());                       
	 			             fl.close(); 
	 		    		}
	 		    		 
	 		    		arr.put("success", 1);
	 		    		arr.put("message", "Saved Successfully!");
	 		    	}
	 		    	else
	 		    	  arr.put("message", "Title required!");
	 			break;
	 		    case "edit":
	 		    	id=Integer.parseInt(request.getParameter("id"));
	 		    	title=request.getParameter("title");  
	 		    	relate=CommonFuns.cint(request.getParameter("booking")); 
	 		    	caption=request.getParameter("caption");  
	 		    	alt=request.getParameter("alternatetext");  
	 		    	desc=request.getParameter("description"); 
	 		    	img=request.getFile("img");
	 		    	cobj= galleries.findById(id);
	 				if(cobj==null)
	 				{
	 					arr.put("success", 0);
	 					arr.put("message", "Not Exists!");
	 				}
	 				else
	 				{
	 					if(title!="")
	 			    	{
	 					 
	 						cobj= galleries.findById(id);
		 		    		cobj.setTitle(title); 
		 		    		cobj.setRelate(relate);  
		 		    		cobj.setAlternatetext(alt);
		 		    		cobj.setDescription(desc);
		 		    		cobj.setCaption(caption);
		 		    		galleries.save(cobj);
		 		    		if(img.getSize()>0)
		 		    		{
		 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\galleries\\"; 
		 						(new File(fpath+cobj.getId())).createNewFile();
		 			             FileOutputStream fl;
		 			             fl=new FileOutputStream(fpath+cobj.getId()); 
		 			             fl.write(img.getBytes());                       
		 			             fl.close(); 
		 		    		}
	 					arr.put("success", 1);
	 					arr.put("message", "Saved Successfully!");
	 					 
	 			    	}
	 					else
	 				    	  arr.put("message", "Name required!");
	 				}
	 		    	break;
	 		     
	 		}
	 		return arr;
	 	}
	 
	 @GetMapping({"/admin/usersadd","/admin/usersadd/{isclient}"})
	    public String usersadd(@PathVariable Optional<Integer> isclient,Model m){   
		  
		   m.addAttribute("isclient",isclient.orElse(0)); 
			 
		   m.addAttribute("types",LoginTypes.values());
		  
	        return "admin/usersadd";
	    } 
	 
	 @GetMapping({"/admin/usersedit/{id}","/admin/usersedit/{id}/{isclient}"})
	    public String usersedit(@PathVariable int id,@PathVariable Optional<Integer> isclient,Model m){       
			m.addAttribute("user", members.findById(id));   
			 m.addAttribute("types",LoginTypes.values());
			   m.addAttribute("isclient",isclient.orElse(0)); 
	        return "admin/usersedit";
	    } 
	 
 
	 
	 @PostMapping("/admin/users")
	 @ResponseBody
	 	public Hashtable<String,Object>  users(MultipartHttpServletRequest  request) throws IOException
	 	{
	 		Hashtable<String,Object> arr=new Hashtable<String,Object>();
	 		String action=request.getParameter("action");
	 		arr.put("success", 0);
	 		Users cobj;
	 		String type,firstname,lastname ,email,mobile,password;  
	 		MultipartFile img ;
	 		int id;
	 		int status;
	 		try {
	 		switch(action)
	 		{
	 		case "readall":
	 			arr.put("success", 1);
	 			arr.put("data", members.findAll());
	 			break;
	 		case "read":			
	 			 id=Integer.parseInt(request.getParameter("id"));
	 			cobj= members.findById(id);
	 			if(cobj==null)
	 			{
	 				arr.put("success", 0);
	 				arr.put("message", "Not Exists!");
	 			}
	 			else
	 			{
	 				arr.put("success", 1);
	 			   arr.put("data",cobj);
	 			}
	 			break;
	 		    case "add":
	 		    	firstname=request.getParameter("firstname");  
	 		    	lastname=request.getParameter("lastname");  
	 		    	email=request.getParameter("email");  
	 		    	mobile=request.getParameter("mobile");  
	 		    	password=request.getParameter("password");  
	 		    	type=request.getParameter("type");  

 		    		if(!adminService.validation_Password(password))
 		    		      throw new Exception("Invalid Password!! Please follow password policies");
 		    		
	 		    	img=request.getFile("img"); 
	 		    		cobj=new Users();
	 		    		cobj.setType(type);
	 		    		cobj.setFirstname(firstname); 
	 		    		cobj.setLastname(lastname); 
	 		    	    cobj.setEmail(email);
	 		    	    cobj.setMobile(mobile);
	 		    	    cobj.setStatus(1);
	 		    	    cobj.setPassword(password);
	 		    		members.save(cobj);
 		    	        adminService.changeUserPassword(cobj, password);
	 		    		if(img.getSize()>0)
	 		    		{
	 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimgs\\"; 
	 						(new File(fpath+cobj.getId())).createNewFile();
	 			             FileOutputStream fl;
	 			             fl=new FileOutputStream(fpath+cobj.getId()); 
	 			             fl.write(img.getBytes());                       
	 			             fl.close(); 
	 		    		}
	 		    		 

	 		    		arr.put("success", 1);
	 		    		arr.put("message", "Saved Successfully!");
	 		    	 
	 			break;
	 		    case "edit":
	 		    	id=Integer.parseInt(request.getParameter("id"));
	 		    	firstname=request.getParameter("firstname");  
	 		    	lastname=request.getParameter("lastname");   
	 		    	mobile=request.getParameter("mobile");  
	 		    	status=CommonFuns.cint(request.getParameter("status"));
	 		    	password=request.getParameter("password");  
	 		    	if(password!=null && !password.isBlank())
 		    		  if(!adminService.validation_Password(password))
 		    		      throw new Exception("Invalid Password!! Please follow password policies");
	 		    	img=request.getFile("img"); 
	 						cobj= members.findById(id);
		 		    		cobj.setFirstname(firstname); 
		 		    		cobj.setLastname(lastname);  
		 		    	    cobj.setMobile(mobile);
		 		    	    cobj.setStatus(status);
		 		    	    members.save(cobj);
		 		    	    if(password!=null && !password.isBlank())
		 		    	        adminService.changeUserPassword(cobj, password);
		 		    		if(img.getSize()>0)
		 		    		{
		 		    			 String fpath = (new FileSystemResource("")).getFile().getAbsolutePath()+"\\uploads\\profileimgs\\"; 
		 						(new File(fpath+cobj.getId())).createNewFile();
		 			             FileOutputStream fl;
		 			             fl=new FileOutputStream(fpath+cobj.getId()); 
		 			             fl.write(img.getBytes());                       
		 			             fl.close(); 
		 		    		} 
		 		    		 
	 					arr.put("success", 1);
	 					arr.put("message", "Saved Successfully!");
	 					 
	 			    	 
	 		    	break;
	 		     
	 		}
	 		}
	 		catch(Exception e)
	 		{ 
					arr.put("success", 0);
					if( e.getMessage().contains("Duplicate"))
						arr.put("message", "Duplicate Email/Mobile");
					else
					arr.put("message", e.getMessage());
	 		}
	 		return arr;
	 	}
	 
	 
	 @GetMapping("/admin/bookings")
	    public String bookings(Model m){      
		    List<Hashtable<String,Object>> lst = new ArrayList<>();
		    Hashtable<String, Object> arr = null ;
		    for(Booking  c:bookings.findAll())
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
		    	arr.put("incharge","NA");
		    	if(c.getIncharge()>0) 
			    	arr.put("incharge",members.findById(c.getIncharge()).getName());
		    	arr.put("user", members.findById(c.getUser()).getName());  
		    	arr.put("creationtime", c.getCreationtime());  
		    	lst.add(arr);
		    }
		    m.addAttribute("bookings",lst);
	        return "admin/bookings";
	    } 
	 
	 @GetMapping("/admin/processbooking/{id}")
	    public String processbooking(@PathVariable int id,Model m){ 
		 List<Hashtable<String,Object>> lst=new ArrayList<>(); 
			Hashtable<String,Object> arr=new Hashtable<String,Object>();
		 
		    Booking booking=bookings.findById(id);
		    Subcategories obj = subcategories.findById(booking.getSubcategory());
		    m.addAttribute("booking", booking); 
		    
		    for(Bookingevents b:bookevents.findByBooking(id))
		 			{
		 				arr=new Hashtable<String,Object>();
		 				arr.put("id", b.getId());
		 				arr.put("title", b.getTitle());
		 				arr.put("venue", b.getVenue());
						arr.put("guests", b.getGuests());		 				
		 				arr.put("location",cities.findById(b.getLocation()).getName());
		 				arr.put("time",CommonFuns.Formatdt(b.getStart())+" to "+CommonFuns.Formatdt(b.getEnd()));
		 				arr.put("status",Eventstatus.getName( b.getStatus())); 
		 				lst.add(arr);
		 			}
		 		    m.addAttribute("events", lst); 
		    
		    m.addAttribute("payments", bookpayments.findByBooking(id)); 
		    m.addAttribute("status", Bookingstatus.getName(booking.getStatus()));
		    m.addAttribute("paid",bookpayments.PaidForBooking(id)); 
		    if(booking.getIncharge()>0)
		       m.addAttribute("incharge", members.findById(booking.getIncharge())); 
		    m.addAttribute("users", members.findByTypeAndStatus(LoginTypes.SUBADMIN.name(),1)); 
		    m.addAttribute("category", categories.findById(obj.getCategory())); 
		    m.addAttribute("subcategory", obj);   
		    m.addAttribute("statuses",Bookingstatus.values());

		    m.addAttribute("user", members.findById(booking.getUser()));    
		    
	        return "admin/processbooking";
	    }  
	 
	 
	 @PostMapping("/admin/bookings")
	 @ResponseBody
	    public Hashtable<String,Object> bookings(HttpServletRequest request,Model m){       
		 Hashtable < String, Object > arr = new Hashtable < > ();  
		 try {
		 int booking=CommonFuns.cint(request.getParameter("booking"));
    	 int status=CommonFuns.cint(request.getParameter("status")); 
    	 int incharge=CommonFuns.cint(request.getParameter("incharge")); 
    	    
    	 
    	 Booking bobj=bookings.findById(booking); 
    	 bobj.setStatus(status);
    	 bobj.setIncharge(incharge);
    	 bookings.save(bobj);
    	 arr.put("success",1);
		 arr.put("message", "Updated Successfully!");
    	 }
		 catch(Exception e)
		 {
			 arr.put("success",0);
			 arr.put("message", e.getMessage());
		 }
	     return arr;
	    }  
		
		 @GetMapping("/admin/contactus")
	    public String contactus(Model m){      
		    m.addAttribute("contactus", contactus.findAll());
	        return "admin/contactus";
	    } 
}