package com.example.travelmaker.travelmaker.Models;

import java.util.Date;


public class User {

	public String userName,email,password,password2, type;

	//protected Date birthDay;

	public User(String userName, String email,String password, String password2) {
		this.userName = userName;
		this.email = email;
		this.password = password;
		this.password2 = password2;
		this.type = type;
	//	this.birthDay = birthDay;
	}


}
