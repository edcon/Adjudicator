package com.erc.java.adjudicator;

public class Room {
String name;
String access;

public Room(String name, String access){
	this.name = name;
	this.access = access;
}

public String getName(){
	return name;
}

public String getAccess(){
	return access;
}

}
