package com.example.travelmaker.travelmaker.Models;

public class User {

	public String userName,email,password,password2, type;

	//protected Date birthDay;

	public User(String userName, String email,String password, String type) {
		this.userName = userName;
		this.email = email;
		this.password = password;
//		this.password2 = password2;
		this.type = type;
	//	this.birthDay = birthDay;
	}

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getuserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setuserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
