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
public class ClientController { 
	
 
	 
	@Autowired 
	private LoginsService clientService;  
	  
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
	
    @Resource
	  private CardcheckRepository cardchecks;
    
    
	 @PostMapping("/home/registerdb") 
	    @ResponseBody
		   public Hashtable < String, Object > registerdb( HttpServletRequest request,Model model)   {
		    Hashtable < String, Object > arr = new Hashtable < > ();  

		     String firstname = request.getParameter("firstname");   
		     String lastname = request.getParameter("lastname");      
		     String email =  request.getParameter("email");    
		     String mobile =  request.getParameter("mobile");      
		      
		     List < String > errors = new ArrayList < String > ();  
		      
		     
		     if (email.trim() .isBlank() || email == null)
			       errors.add("Email");
		     else
			     if(members.findByEmail(email)!=null)
			    	 errors.add("Email Exists!!");
		     if (  firstname == null || firstname.trim().isBlank()) errors.add("First name");
		     if (  lastname == null || lastname.trim().isBlank()) errors.add("Last name");
		     if (  mobile == null || mobile.trim().isBlank()) errors.add("Mobile"); 
		     else
			     if(members.findByMobile(mobile)!=null)
			    	 errors.add("Mobile Exists!!");
		     
		     arr.put("success",  0); 
		     if (errors.size() == 0) {
		       String password= CommonFuns.generatePassword();
		       Users u=new Users(); 
		       u.setType(LoginTypes.CLIENT.name());
		       u.setFirstname(firstname); 
		       u.setLastname(lastname); 
		       u.setEmail(email);  
		       u.setMobile(mobile); 
		       u.setPassword(mobile);
		       u.setStatus(1);
		       u.setCreationtime(CommonFuns.ctm());
		       members.save(u); 
		       request.getSession().setAttribute("password", password);
		       clientService.changeUserPassword(u,password);
		       
		       //autologin
		       
		       Authentication auth = new UsernamePasswordAuthenticationToken (u.getEmail (),u.getPassword (),clientService.getAuthorities(u.getType()));
              SecurityContextHolder.getContext().setAuthentication(auth);
	           request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
	            
		    	   arr.put("success",  1); 
			       arr.put("message",  " Created successfully!.");   
		     } else
		    	 arr.put("message",  "The following fields contains errors: " + errors.stream().collect(Collectors.joining(", ")));

		     return  arr;
		   }
	 @GetMapping("/home/client-login")
	    public String clientlogin(Authentication auth,Model model){  
	    	
	    	 if(auth!=null)
	 		    if(auth.isAuthenticated())
	 		    {
	 		    	if(!auth.getAuthorities().isEmpty())
	 		    	{
	 		    		for(LoginTypes l:LoginTypes.values())
	 		    		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(l.toString())))
	 		              	return "redirect:/"+l.toString().toLowerCase()+"/dashboard"; 
	 		    	}
	 		    }
	        return "client/login";
	    }  
	 
	 @GetMapping("/home/forgotpassword")
	    public String forgotpassword(Authentication auth,Model model){  
	    	
	    	 if(auth!=null)
	 		    if(auth.isAuthenticated())
	 		    {
	 		    	if(!auth.getAuthorities().isEmpty())
	 		    	{
	 		    		for(LoginTypes l:LoginTypes.values())
	 		    		if(auth.getAuthorities().contains(new SimpleGrantedAuthority(l.toString())))
	 		              	return "redirect:/"+l.toString().toLowerCase()+"/dashboard"; 
	 		    	}
	 		    }
	        return "client/forgotpassword";
	    }  
	 
	 
	 @PostMapping("/home/resetpassword")
	    public String resetpassword(Authentication auth,HttpServletRequest request,Model m)
	    {
	    	String username=request.getParameter("username");
	    	String newpassword=request.getParameter("newpassword");
	    	String confirmpassword=request.getParameter("confirmpassword");
	    	
	    	Users user = clientService.findByUsername(username);
	    		    if(newpassword.compareTo(confirmpassword)>0)
	    		    {
	    		    	m.addAttribute("updatepassword_errors", "New Password and Confirm Password Field do not match  !!");
	    		    }
	    		    else if (user==null) {
	    		        m.addAttribute("updatepassword_errors", "Inavlid User!");
	    		    }
	    		    else if(!clientService.validation_Password(newpassword))
	    		    {
	    		    	  m.addAttribute("updatepassword_errors", "Inavlid New Password.Please follow specified policies.");
	    		    }
	    		    else
	    		    {
	    		    	clientService.changeUserPassword(user, newpassword);
	    		      m.addAttribute("updatepassword_success", "Password updated successfully");
	    		    }
	    	return forgotpassword(auth,m);
	    } 
	 
	 
	 @PostMapping("/home/cloginauth")
	   public String cloginauth(HttpServletRequest request)
	   {
		   	 String userName=request.getParameter("username");
	    	 String password=request.getParameter("password");   
	    	 Users userDetails = clientService.findByUsername(userName); 
	    	 boolean error=false;
	    	 
	    	 if(userDetails!=null && userDetails.getStatus()==1)
	         if(clientService.checkIfValidOldPassword(userDetails, password) && userDetails.getStatus()==1)
	         {  
				  	  	 	
	              Authentication auth = new UsernamePasswordAuthenticationToken (userDetails.getEmail (),userDetails.getPassword (),clientService.getAuthorities(userDetails.getType()));
	               
	              SecurityContextHolder.getContext().setAuthentication(auth);
	              request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", SecurityContextHolder.getContext());
	              return "redirect:/"+userDetails.getType().toLowerCase()+"/dashboard"; 
	         }
	         else
	            error=true;
	       if(error)
	       { 
	    		   return "redirect:/home/client-login?error=1";  
	       }
	       return "redirect:/home/client-login?error=1"; 
	   }
	 
	 
	 @GetMapping("/client/dashboard")
	    public String dashboard(HttpServletRequest request,Model m){       
		    if(request.getSession().getAttribute("password") != null)
		    {
		    	m.addAttribute("password", request.getSession().getAttribute("password"));
		    }
	        return "client/dashboard";
	    }  

	 
	    @GetMapping("/client/editprofile")
	    public String editprofile(Model model) {
	    	Users user = clientService.findByUsername(
	  		      SecurityContextHolder.getContext().getAuthentication().getName());
	    	 
	    	model.addAttribute("status",user.getStatus());  
	    	model.addAttribute("firstname",user.getFirstname()); 
	    	model.addAttribute("lastname",user.getLastname()); 
	    	model.addAttribute("mobile", user.getMobile() ); 
	    	model.addAttribute("email", user.getEmail());  
	    	model.addAttribute("id",user.getId());   
	    	
	    	return "client/myprofile";
	    } 
	    
	    @PostMapping("/client/updateprofile") 
		   public String updateprofile( MultipartHttpServletRequest request,Model model) throws  Exception   {
		     Hashtable < String, Object > arr = new Hashtable < > ();  
		     
		     List < String > errors = new ArrayList < String > ();
		     
		     Users user = clientService.findByUsername(
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
	     
	 
	 
	 @GetMapping("/client/changepassword")
	    public String changepassword( Model m){       
	        return "client/changepassword";
	    }  
	 
	 @PostMapping("/client/updatepassword")
	    public String updatepassword(HttpServletRequest request,Model m)
	    {
	    	String password=request.getParameter("password");
	    	String newpassword=request.getParameter("newpassword");
	    	String confirmpassword=request.getParameter("confirmpassword");
	    	
	    	Users user = clientService.findByUsername(
	    		      SecurityContextHolder.getContext().getAuthentication().getName());
	    		    if(newpassword.compareTo(confirmpassword)>0)
	    		    {
	    		    	m.addAttribute("updatepassword_errors", "New Password and Confirm Password Field do not match  !!");
	    		    }
	    		    else if (!clientService.checkIfValidOldPassword(user, password)) {
	    		        m.addAttribute("updatepassword_errors", "Inavlid Current Password");
	    		    }
	    		    else if(!clientService.validation_Password(newpassword))
	    		    {
	    		    	  m.addAttribute("updatepassword_errors", "Inavlid New Password.Please follow specified policies.");
	    		    }
	    		    else
	    		    {
	    		    	clientService.changeUserPassword(user, newpassword);
	    		      m.addAttribute("updatepassword_success", "Password updated successfully");
	    		    }
	    	return changepassword(m);
	    } 
	 
	 @GetMapping("/client/bookingadd")
	    public String bookingadd(HttpServletRequest request,Model m){       
		    m.addAttribute("categories", categories.findAll());
	        return "client/bookingadd";
	    }  
	 
	 @PostMapping("/client/getPackages/{id}")
	 @ResponseBody
	    public Hashtable<String,Object> getPackages(@PathVariable int id,HttpServletRequest request,Model m){       
		 Hashtable < String, Object > arr = new Hashtable < > ();  
		 arr.put("packages", subcategories.findByCategoryAndStatus(id,1));
	     return arr;
	    }  
	 
	 @PostMapping("/client/getPackage/{id}")
	 @ResponseBody
	    public Hashtable<String,Object> getPackage(@PathVariable int id,HttpServletRequest request,Model m){       
		 Hashtable < String, Object > arr = new Hashtable < > ();  
		   Subcategories obj = subcategories.findById(id);
		   arr.put("category", categories.findById(obj.getCategory())); 
		   arr.put("package", obj); 
		   arr.put("features", features.findAllById(subcatfeatures.getFeatures(id)) );
	     return arr;
	    }  
	 
	 @PostMapping("/client/createbooking")
	 @ResponseBody
	    public Hashtable<String,Object> createbooking(HttpServletRequest request,Model m){       
		 Hashtable < String, Object > arr = new Hashtable < > ();  
		 try {
		 int category=CommonFuns.cint(request.getParameter("category"));
    	 int pkge=CommonFuns.cint(request.getParameter("package")); 
    	 String title=request.getParameter("title"); 
    	 Date fromdate=Date.valueOf(request.getParameter("fromdate")); 
    	 Date todate=Date.valueOf(request.getParameter("todate")); 
    	 String details=request.getParameter("details"); 
    	 Subcategories pobj = subcategories.findById( pkge);
    	 if(pobj.getStatus()==0)
    		 throw new Exception("Inactive package selected!");
    	 if((fromdate.compareTo(todate)>0)) 
    		 throw new Exception("Inactive Date(s) selected!");
    	 if(details.length()<100) 
    			 throw new Exception("Details should have atleast 100 letters!");
    	 if(title.length()<20) 
			 throw new Exception("Title should have atleast 20 letters!");
    	 Users user =clientService.findByUsername(
	  		      SecurityContextHolder.getContext().getAuthentication().getName());   
    	 
    	 Booking bobj=new Booking();
    	 bobj.setCategory(category);
    	 bobj.setDetails(details);
    	 bobj.setFromdate(fromdate);
    	 bobj.setTodate(todate);
    	 bobj.setPrice(pobj.getPrice());
    	 bobj.setSubcategory(pkge);
    	 bobj.setTitle(title);
    	 bobj.setUser(user.getId());
    	 bobj.setStatus(Bookingstatus.Booked.acode());
    	 bookings.save(bobj);
    	 arr.put("success",1);
		 arr.put("message", "Created Successfully!");
    	 }
		 catch(Exception e)
		 {
			 arr.put("success",0);
			 arr.put("message", e.getMessage());
		 }
	     return arr;
	    }  
	 
	 @GetMapping("/client/bookings")
	    public String bookings(Model m){      
		    List<Hashtable<String,Object>> lst = new ArrayList<>();

	    	 Users user =clientService.findByUsername(
		  		      SecurityContextHolder.getContext().getAuthentication().getName());   
		    Hashtable<String, Object> arr = null ;
		    for(Booking  c:bookings.findByUser(user.getId()))
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
		    	arr.put("creationtime", c.getCreationtime());  
		    	lst.add(arr);
		    }
		    m.addAttribute("bookings",lst);
	        return "client/bookings";
	    }
	 
	 
	 @GetMapping("/client/viewbooking/{id}")
	    public String viewbooking(@PathVariable int id,Model m){     

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
		    m.addAttribute("category", categories.findById(obj.getCategory())); 
		    m.addAttribute("subcategory", obj); 
		    m.addAttribute("features", features.findAllById(subcatfeatures.getFeatures(id)) );
	        return "client/viewbooking";
	    }  
	 
	 
	 @PostMapping("/client/addpayment")
	 @ResponseBody
	    public Hashtable<String,Object> addpayment(HttpServletRequest request,Model m){       
		 Hashtable < String, Object > arr = new Hashtable < > ();  
		   List<String> errors=new ArrayList<String>(); 
	    	 arr.put("success",0);
		 try {
		 int booking=CommonFuns.cint(request.getParameter("booking"));
    	 float amount=CommonFuns.cint(request.getParameter("amount")); 
    	 

    	    String transactionid = request.getParameter("transactionid");     
    	     String paymentMethod=request.getParameter("paymentMethod");
    	     String cardno = request.getParameter("cardno");
    	     String exp = request.getParameter("exp");
    	     String cvv = request.getParameter("cvv");
    	  
    	     if(	paymentMethod	==null	||	paymentMethod.isBlank())	 
    	    	 errors.add("payment method");
    	     else  if(paymentMethod.compareTo("CARD")==0)
    	      {
    	        if(	cardno	==null	||	cardno.isBlank())	 errors.add("card no");
    	        if(	exp	==null	||	exp.isBlank())	 errors.add("expire date");
    	        if(	cvv	==null	||	cvv.isBlank())	 errors.add("cvv");
    	       if(errors.size()==0)
    	       {
    	        Cardcheck cc = cardchecks.findByCardno(cardno);
    	        if (cc != null) {  
    	            if (cc.getCvvcode().compareTo(cvv) == 0 && cc.getExpiry().compareTo(exp) == 0 && cc.getValid() == 1) {
    	            	transactionid=UUID.randomUUID().toString();
    	           } else
    	        	   errors.add("Card Payment Failed! Please check card details");
    	      } else
    	    	  errors.add("Card is not exists!");
    	       }
    	      }
    	      else {      
    	           if (transactionid.trim().isBlank()  || transactionid == null) errors.add("Transaction id");

    	      }
    	      
    	 
    	 if(errors.size()==0)
    	 {
    	 Bookingpayments bobj=new Bookingpayments(); 
    	 bobj.setBooking(booking);
    	 bobj.setPaymentmode(paymentMethod);
    	 bobj.setAmount(amount) ;
    	 bobj.setTransactionid(transactionid);
    	 bookpayments.save(bobj);
    	 arr.put("success",1);
		 arr.put("message", "Paid Successfully!");
    	 }
    	 else 
    		        arr.put("message","The following fields contains errors: "+errors.stream().collect(Collectors.joining(", ")));
    			
    	 }
		 catch(Exception e)
		 {
			 arr.put("success",0);
			 arr.put("message", e.getMessage());
		 }
	     return arr;
	    }  
}