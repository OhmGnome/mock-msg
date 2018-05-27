package com.mock.msg.beans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mock.msg.model.User;
/*
 * holds a user var that lives from page to page
 */

@Component
@Scope("session")
public class UserBean {

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
