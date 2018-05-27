package com.mock.msg.beans;

import org.springframework.stereotype.Component;

@Component
public class NavBean {
	
	public String login(){
		return "login";
	}
	
	public String registrar(){
		return "registrar";
	}
	
	public String userHome(){
		return "userHome";
	}
	
	public String home(){
		return "home";
	}
	
	public String manage(){
		return "manage";
	}
	
	public String viewUser(){
		return "viewUser";
	}
	
}
