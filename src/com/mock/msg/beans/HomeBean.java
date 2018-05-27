package com.mock.msg.beans;

import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.mock.msg.model.Tell;
import com.mock.msg.model.User;
import com.mock.msg.beans.dao.TellDao;
import com.mock.msg.beans.dao.UserDao;

/*
 * bean for the home page. classes that should be reconstructed everytime the page loads should be @request scope, 
 * any variables that need to be passed from bean to bean should be @session scope
 */
@Component
//@Scope("request")
@Scope("session")
public class HomeBean {
	
	Logger log = LoggerFactory.getLogger(HomeBean.class);

	
	@Autowired
	private TellBean tellBean;
	@Autowired
	private UserBean userBean;
	
	@Autowired
	private TellDao tellDao;
	@Autowired
	private UserDao userDao;

	//getters and setters for @Autowired are unecessary
	//but getsets for these are
	private List<Tell> banter;
	private List<User> users;

	@PostConstruct
	public void init() {
		 banter = tellDao.getTells();
		 log.debug("home construct setting banter: {}", banter.size());
		 users = userDao.getUsers();
	}
	

	public TellDao getProfileDao() {
		return tellDao;
	}

	public void setProfileDao(TellDao ProfileDao) {
		this.tellDao = ProfileDao;
	}

	public TellBean getTellBean() {
		return tellBean;
	}

	public void setTellBean(TellBean tellBean) {
		this.tellBean = tellBean;
	}

	public List<Tell> getBanter() {
		return banter;
	}

	public void setBanter(List<Tell> banter) {
		this.banter = banter;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
