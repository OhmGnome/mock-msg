package com.mock.msg.beans;

import java.util.ArrayList;
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
 * the Logged in user's secured pages
 * 
 */
@Component
//@Scope("session")
@Scope("request")
public class ProfileBean {

	Logger log = LoggerFactory.getLogger(ProfileBean.class);

	@Autowired
	private TellDao tellDao;
	@Autowired
	private UserDao userDao;

	@Autowired
	private AuthBean authBean;
	@Autowired
	private TellBean tellBean;
	@Autowired
	private UserBean userBean;

	//should be using authBean.getUsername() instead of a static user
	private User user;
	private Tell tell;

	private List<Tell> followedTells;
	private List<Tell> tells;
	private List<User> follows;
	private List<User> followers;

	@PostConstruct
	private void init() {
		if (authBean.isLoggedIn()) {
			user = userDao.getUserByName(authBean.getUsername());
			this.tells = tellDao.getTells();
			this.follows = userDao.getFollows(user);
			this.followers = userDao.getFollowers(user);
			this.followedTells = tellDao.getTellsByFollows(user);
		} else {
			tells = new ArrayList<>();
			follows = new ArrayList<>();
			followers = new ArrayList<>();
			followedTells = new ArrayList<>();
		}
		this.tell = new Tell();
	}
	
	public boolean following(){
		boolean following = false;
		if (follows.contains(userBean.getUser())){
				following = true;
		}
		return following;
	}
	
	public void unFollow() {
		if (userDao.getUserByName(authBean.getUsername()) == null) {
			log.debug("unFollow() auth null");
			System.out.println("unFollow() auth null");
		}else if (userBean.getUser() == null) {
				log.debug("unFollow() user null");
				System.out.println("unFollow() user null");
			
		} else {
			userDao.unFollow(userDao.getUserByName(authBean.getUsername()),
					userBean.getUser());
			this.follows = userDao.getFollows(userDao.getUserByName(authBean
					.getUsername()));
		}
	}

	public void follow() {
		User authUser = user;
			if (authUser == null){
			System.out.println("follow()Auth null");	
		}else if (userBean.getUser() == null) {
			System.out.println("follow()userBean null");
			
		} else{
			System.out.println("follow() not null");
		}
	}


	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}
	
	public void makeTell() {
		System.out.println("tell has " +tell.getMessage());
		tell.setUser(userDao.getUserByName(authBean.getUsername()));
		System.out
				.println("a new tell has user " + tell.getUser().getName());
		System.out.println("a new tell has content: " + tell.getMessage());
		
		log.debug("a new tell has user {}", tell.getUser().getName());
		log.debug("a new tell has content: {}", tell.getMessage());
		tellDao.saveTell(tell);
		this.tells = tellDao.getTells();
		this.followedTells = tellDao.getTellsByFollows(userDao.getUserByName(authBean.getUsername()));
		tell = new Tell();
	}
	
		
	public String viewUserByTell(){
		userBean.setUser(tellBean.getTell().getUser());
		return "viewUser";
	}


	public TellDao getProfileDao() {
		return tellDao;
	}

	public void setProfileDao(TellDao ProfileDao) {
		this.tellDao = ProfileDao;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public AuthBean getAuthBean() {
		return authBean;
	}

	public void setAuthBean(AuthBean authBean) {
		this.authBean = authBean;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Tell getTell() {
		return tell;
	}

	public void setTell(Tell tell) {
		this.tell = tell;
	}

	public List<Tell> getTells() {
		return tells;
	}

	public void setTells(List<Tell> tells) {
		this.tells = tells;
	}


	public List<User> getFollows() {
		return follows;
	}

	public void setFollows(List<User> follows) {
		this.follows = follows;
	}

	public List<Tell> getFollowedTells() {
		return followedTells;
	}

	public void setFollowedTells(List<Tell> followedTells) {
		this.followedTells = followedTells;
	}

}