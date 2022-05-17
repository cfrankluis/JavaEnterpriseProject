package com.modle;

import lombok.Data;

@Data
public class User {
	
	private String username;
	private String password;
	private String email;
	private String page;
	
	/**
	 * @param username
	 * @param password
	 * @param email
	 */
	public User(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	public User() {
		super();
	}

	
	
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getUsername() {
		return this.username;
	}


	public String getPassword() {
		return this.password;
	}


	public String getPage() {
		return this.page;		
	}


	public String getEmail() {
		return this.email;		
	}
	
	
}
