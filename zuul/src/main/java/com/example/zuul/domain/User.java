package com.example.zuul.domain;

import java.util.Date;

public class User {
	
	private String userName;
	private String password;
	Date lastPasswordResetDate;
	
	public User() {}
	
	public User(String userName,String password) {
		this.userName=userName;
		this.password=password;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Date getLastPasswordResetDate() {
		return lastPasswordResetDate;
	}

	public void setLastPasswordResetDate(Date lastPasswordResetDate) {
		this.lastPasswordResetDate = lastPasswordResetDate;
	}
	
	
	
	
	
	

}
