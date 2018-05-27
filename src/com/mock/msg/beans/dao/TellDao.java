package com.mock.msg.beans.dao;

import java.util.List;

import com.mock.msg.model.Tell;
import com.mock.msg.model.User;

public interface TellDao {
	public void saveTell(Tell tell);
	public List<Tell> getTells();
	public List<Tell> getTellsByUser(User user);
	public List<Tell> getTellsByFollow(User follow);
	public List<Tell> getTellsByFollows(User user);
}
