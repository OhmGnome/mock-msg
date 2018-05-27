package com.mock.msg.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mock.msg.model.User;
import com.mock.msg.beans.dao.UserDao;

/*
 * Stores the current user
 * All methods that need the signed in user should access it through authBean.getName()
 */

@Component
@Scope("session")
public class AuthBean {

	@Autowired
	private UserDao userDao;
	
	private User user;
	
	public boolean login(User user){
		if (user != null && user.getName() != null
				&& user.getPassword() != null){
			
			String username = user.getName().trim();
			String password = user.getPassword().trim();
			
			if (!username.isEmpty() && !password.isEmpty()){
				
				User dbHasUser = userDao.getUserByName(username);
				
				if (dbHasUser != null && dbHasUser.getPassword().equals(password)){
					
					this.user = dbHasUser;
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isLoggedIn(){
		return user != null;
	}
	
	public String logout(){
		user = null;
		return "logout";
	}
	
	public String getUsername(){
		if (user != null){
			return user.getName();
		}else{
			return null;
		}
	}	
}
