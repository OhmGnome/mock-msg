package com.mock.msg.model;

// Generated May 18, 2015 9:42:35 AM by Hibernate Tools 4.3.1

/**
 * FollowId generated by hbm2java
 */
public class FollowId implements java.io.Serializable {

	private int followId;
	private int userId;

	public FollowId() {
	}

	public FollowId(int followId, int userId) {
		this.followId = followId;
		this.userId = userId;
	}

	public int getFollowId() {
		return this.followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof FollowId))
			return false;
		FollowId castOther = (FollowId) other;

		return (this.getFollowId() == castOther.getFollowId())
				&& (this.getUserId() == castOther.getUserId());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getFollowId();
		result = 37 * result + this.getUserId();
		return result;
	}

}
