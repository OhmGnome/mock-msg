package com.mock.msg.beans.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mock.msg.model.Follow;
import com.mock.msg.model.FollowId;
import com.mock.msg.model.User;
import com.mock.msg.beans.dao.UserDao;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {
	
	Logger log = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public User saveUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		User result = null;
			
			try {
				session.save(user);
				
				result = user;
			} catch (Exception e) {
				//log
				e.printStackTrace();
				return null;
			}
				
		return result;
	}

	
	@Override
	public User getUserByName(String username) {
		Session session = sessionFactory.getCurrentSession();
		return (User)  session.createQuery("SELECT u FROM User u WHERE u.name = :name").setString("name", username).uniqueResult();
	}
	

	@Override
	public List<User> getUsers() {
		Session session = sessionFactory.getCurrentSession();
		return session.createQuery("From User").list();
	}

	@Override
	public String follow(User user, User follow) {
		Session session = sessionFactory.getCurrentSession();
		Follow stalk = new Follow();

    	stalk.setUserByUserId(user);
		stalk.setUserByFollowId(follow);
		stalk.setId(new FollowId());
		stalk.getId().setUserId(user.getUserId());
		stalk.getId().setFollowId(follow.getUserId());
		
		session.save(stalk);
		
		return "follow";
	}

	@Override
	public String unFollow(User user, User follow) {
		Session session = sessionFactory.getCurrentSession();
		Follow unstalk = new Follow();
		unstalk = (Follow) session
				.createQuery(
						"SELECT f FROM Follow f WHERE f.userByUserId = :userId AND f.userByFollowId = :followId")
				.setInteger("userId", user.getUserId())
				.setInteger("followId", follow.getUserId()).uniqueResult();
		
		log.debug("followObj has followId: {} ", unstalk.getUserByFollowId().getUserId().intValue() );
		log.debug("followObj has userId: {}", unstalk.getUserByUserId().getUserId().intValue());
		
		session.delete(unstalk);
		return "unFollow";
	}


	@Override
	public List<User> getFollowers(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		Query q = session
				.createQuery(
						"SELECT u FROM User u, Follow f WHERE u.userId = f.userByUserId  AND f.userByFollowId =:userId")
				.setInteger("userId", user.getUserId());
		List<User> result = q.list();
		return result;
	}
	
	@Override
	public List<User> getFollows(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session
				.createQuery(
						"SELECT u FROM User u, Follow f WHERE u = f.userByFollowId AND f.userByUserId  = :userId")
				.setInteger("userId", user.getUserId());
		List<User> result = q.list();
		return result;
	}
}
