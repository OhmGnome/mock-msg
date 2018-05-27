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
 * a general profile page of of a user account of the twitter clone
 */

@Component
// @Scope("session")
@Scope("request")
public class UserTellsBean {

	Logger log = LoggerFactory.getLogger(UserTellsBean.class);

	@Autowired
	UserDao userDao;
	@Autowired
	TellDao tellDao;

	@Autowired
	UserBean userBean;
	@Autowired
	AuthBean authBean;
	@Autowired
	ProfileBean ProfileBean;


	 private User user;
	private List<User> followers;
	private List<Tell> tells;

	@PostConstruct
	public void init() {
		if (userBean.getUser() != null){
			this.user = userBean.getUser();
				
			User user = userBean.getUser();
			
			System.out.println(" UserTellsBean init, userBean is: "
					+ userBean.getUser().toString());
			log.debug("initializing userTellsBean with authorized user {}",
					userBean.getUser());
			System.out.println(" UserTellsBean init, user is: " + user);
			
			followers = userDao.getFollowers(user);
			tells = tellDao.getTellsByUser(user);
			

		}else if(userDao.getUserByName(authBean.getUsername()) != null) {
			log.debug("initializing userTellsBean with authorized user {}",
					userDao.getUserByName(authBean.getUsername()));
			System.out
					.println("initializing userTellsBean with authorized user"
							+ userDao.getUserByName(authBean.getUsername()));
		} else {
			followers = new ArrayList<>();
			tells = new ArrayList<>();
		}
	}
	
	public void follow() {
		User authUser = new User();

		if ((authUser = userDao.getUserByName(authBean.getUsername())) != null
				&& user != null) {
			userDao.follow(authUser, user);
			followers = userDao.getFollowers(user);
			ProfileBean.setFollows(userDao.getFollows(authUser));
			
			log.debug("unfollow user {}", authUser.getName());
			log.debug("unfollow follow: {}", user.getName());
			
		} else{
			System.out.println("follow() null");
		}

	}

	public void unFollow() {
		User authUser = new User();
		if ((authUser = userDao.getUserByName(authBean.getUsername())) != null
				&& user != null) {
			userDao.unFollow(authUser, user);
			ProfileBean.setFollowers(userDao.getFollowers(authUser));
		} else {
			log.debug("unfollow user {}", authUser.getName());
			log.debug("unfollow follow: {}", user.getName());
		}
	}

	public List<User> getFollows() {
		return followers;
	}

	public void setFollows(List<User> follows) {
		this.followers = follows;
	}

	public List<Tell> getTells() {
		return tells;
	}

	public void setTells(List<Tell> tells) {
		this.tells = tells;
	}

	public List<User> getFollowers() {
		return followers;
	}

	public void setFollowers(List<User> followers) {
		this.followers = followers;
	}

	 public User getUser() {
	 return user;
	 }
	
	 public void setUser(User user) {
	 this.user = user;
	 }

}
