package main;

import java.util.ArrayList;

public abstract class Account {
	private String username;
	private String password;
	private int attempts;
	private boolean lock;
	
	public void lockAccount() {
		
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
}
