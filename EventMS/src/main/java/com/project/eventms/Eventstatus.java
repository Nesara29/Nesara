package com.project.eventms;

public enum Eventstatus {   
	Planned (0,"Planned"),  
	Live (1,"Live"), 
	Completed(2,"Completed"), 
	Cancelled(3,"Cancelled");  
	
	private int acode;
	private String aname;
	Eventstatus(int code,String name)
	{
		this.acode=code;
		this.aname=name;
	}
	
	
	public int acode() {return acode;}
	public String aname() {return aname;} 
	
	public static String getName(int code)
	{
		for(Eventstatus s: Eventstatus.values())
			if(s.acode()==code)
		       return s.aname();
		return "";
	}
}
