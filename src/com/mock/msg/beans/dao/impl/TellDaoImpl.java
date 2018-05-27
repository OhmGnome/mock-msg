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

import com.mock.msg.model.Tell;
import com.mock.msg.model.User;
import com.mock.msg.beans.dao.TellDao;

@Repository
@Transactional
public class TellDaoImpl implements TellDao {

	Logger log = LoggerFactory.getLogger(TellDaoImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void saveTell(Tell tell) {
		Session session = sessionFactory.getCurrentSession();
		session.save(tell);
	}

	@Override
	public List<Tell> getTellsByUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session
				.createQuery(
						"SELECT p FROM Tell p, User u WHERE p.user = u AND u.userId = :userId ORDER BY p.date DESC")
				.setInteger("userId", user.getUserId());
		List<Tell> result = q.list();
		return result;
	}

	@Override
	public List<Tell> getTellsByFollow(User follow) {
		Session session = sessionFactory.getCurrentSession();
		Query q = session
				.createQuery(
						"SELECT t FROM Tell t WHERE t.tellId = :userId ORDER BY t.date DESC")
				.setInteger("tellId", follow.getUserId());
		List<Tell> result = q.list();
		return result;

	}

	@Override
	public List<Tell> getTellsByFollows(User user) {
		Session session = sessionFactory.getCurrentSession();

		// Set<User> follows = user.getFollowsForFollowId();
		// for (User follow : follows){
		// follow.getTelles();
		// }

		Query q = session
				.createQuery(
				// "SELECT t FROM Tell t WHERE t.userId = (SELECT f.followId FROM Follow f WHERE f.userId = :userId) "
				// + "ORDER BY t.date DESC")
																							//user tbl		//who im following		//user colm	//user tb
						"SELECT DISTINCT p FROM Tell p, Follow f, User u WHERE (p.user = u.userId AND u = f.userByFollowId AND f.userByUserId = :userId) OR (p.user = u AND u.userId = :userId)ORDER BY p.date DESC")
				.setInteger("userId", user.getUserId());
		List<Tell> result = q.list();
		return result;
	}

	@Override
	public List<Tell> getTells() {
		Session session = sessionFactory.getCurrentSession();
		Query q = session.createQuery("From Tell p ORDER BY p.date DESC");
		List<Tell> result = q.list();
		log.debug("getTells() returning: {}", result.size());
		return result;
	}
}
