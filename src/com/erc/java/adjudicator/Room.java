package com.erc.java.adjudicator;

public class Room {
String name;
String access;
String role;

public Room(String name, String access, String role){
	this.name = name;
	this.access = access;
	this.role = role;
}

public Room(){
	
}

public String getName(){
	return name;
}

public String getAccess(){
	return access;
}
public String getRole(){
	return role;
}
}
