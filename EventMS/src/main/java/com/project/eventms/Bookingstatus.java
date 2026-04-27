package com.project.eventms;

public enum Bookingstatus {  
	Rejected (-1,"Rejected"), 
	Booked (0,"Booked"), 
	Approved (1,"Approved"), 
	Live (2,"Live"), 
	Completed(3,"Completed");  
	
	private int acode;
	private String aname;
	Bookingstatus(int code,String name)
	{
		this.acode=code;
		this.aname=name;
	}
	
	
	public int acode() {return acode;}
	public String aname() {return aname;} 
	
	public static String getName(int code)
	{
		for(Bookingstatus s: Bookingstatus.values())
			if(s.acode()==code)
		       return s.aname();
		return "";
	}
}
