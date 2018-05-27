package com.mock.msg.beans.dao;

import java.util.List;

import com.mock.msg.model.User;

public interface UserDao {
//	public User getUser(Short userId);
	public List<User> getUsers();
	public String follow(User user, User follow);
	public String unFollow(User user, User follow);
	public List<User> getFollowers(User user);
	public List<User> getFollows(User user);
	public User saveUser(User user);
	public User getUserByName(String username);
}
