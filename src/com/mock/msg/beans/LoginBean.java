package com.mock.msg.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mock.msg.model.User;

@Component
@Scope("session")
public class LoginBean {

	@Autowired
	AuthBean authBean;
	@Autowired 
	ProfileBean ProfileBean;
	
	//unecessary User, using authbean.getUserName()
	private User user;
	
	//unecessary bool, using authbean.isLoggedIN()
	private boolean logged = false;
	


	@PostConstruct
	public void init(){
		user = new User();
	}
	
	public String login(){
		if (authBean.login(user)){
			logged = true;
			return "login";
		}else {
			logged = false;
			return "login-fail";
		}
	}
	
	public String logout(){
		String logout = authBean.logout();
		logged = false;
		ProfileBean = null;
		return logout;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}


	public boolean isLogged() {
		return logged;
	}

	public void setLogged(boolean logged) {
		this.logged = logged;
	}
	
}
