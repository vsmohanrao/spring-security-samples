package com.mu.samples.security.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "user_attempts")
public class UserAttemptsModel implements Serializable {

	private static final long serialVersionUID = -3959341192795307834L;

	@Id
	@Column(name = "username")
	private String userName;
	@Column(name = "attempts")
	private Integer attempts;
	@Column(name = "last_modified")
	private Date lastModified;

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	private UserModel user;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getAttempts() {
		return attempts;
	}

	public void setAttempts(Integer attempts) {
		this.attempts = attempts;
	}

	public Date getLastModified() {
		return lastModified;
	}

	public void setLastModified(Date lastModified) {
		this.lastModified = lastModified;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "UserAttemptsModel [userName=" + userName + ", attempts="
				+ attempts + ", lastModified=" + lastModified.toString()
				+ ", user=" + user + "]";
	}

}
