package com.mock.msg.beans;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mock.msg.model.User;
import com.mock.msg.beans.dao.UserDao;

@Component
@Scope("session")
public class RegisterBean {
	
	@Autowired
	LoginBean loginBean;
	
	@Autowired
	private UserDao userDao;
	
	private User user;
	
	@PostConstruct
	public void postConstruct(){
		user = new User();
	}
	
	public String register() {
		User newUser = userDao.saveUser(user);
		if (newUser != null) {
			this.user = newUser;
			loginBean.setUser(user);
			loginBean.login();
			return "";
		} else {
			return "register-fail";
		}
	}
	


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}