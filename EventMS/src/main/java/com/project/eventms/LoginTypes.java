package com.project.eventms;

public enum LoginTypes {
ADMIN("ADMIN"),  
SUBADMIN("SUBADMIN"),
CLIENT("CLIENT");
	private String name;
LoginTypes(String name) {
	this.name=name;
	// TODO Auto-generated constructor stub
} 
}
