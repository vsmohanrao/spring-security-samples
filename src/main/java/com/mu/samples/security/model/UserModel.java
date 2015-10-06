package com.mu.samples.security.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserModel implements Serializable {

	private static final long serialVersionUID = -2039471075489694401L;

	@Id
	@Column(name = "username")
	private String userName;
	@Column(name = "password")
	private String password;
	@Column(name = "enabled")
	private Integer enabled;
	@Column(name = "account_non_expired")
	private Integer accountNonExpired;
	@Column(name = "account_non_locked")
	private Integer accountNonLocked;
	@Column(name = "credentials_non_expired")
	private Integer credentialsNonExpired;

	@OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
	private UserAttemptsModel userAttempts;

	public UserModel() {

	}

	public UserModel(UserModel userModel) {
		this.userName = userModel.getUserName();
		this.password = userModel.getPassword();
		this.enabled = userModel.getEnabled();
		this.accountNonExpired = userModel.getAccountNonExpired();
		this.accountNonLocked = userModel.getAccountNonLocked();
		this.credentialsNonExpired = userModel.getCredentialsNonExpired();
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Integer getAccountNonExpired() {
		return accountNonExpired;
	}

	public void setAccountNonExpired(Integer accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public Integer getAccountNonLocked() {
		return accountNonLocked;
	}

	public void setAccountNonLocked(Integer accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public Integer getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	public void setCredentialsNonExpired(Integer credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public UserAttemptsModel getUserAttempts() {
		return userAttempts;
	}

	public void setUserAttempts(UserAttemptsModel userAttempts) {
		this.userAttempts = userAttempts;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("UserModel [userName=");
		sb.append(userName);
		sb.append(", password=");
		sb.append(password);
		sb.append(", enabled=");
		sb.append(enabled);
		sb.append(", accountNonExpired=");
		sb.append(accountNonExpired);
		sb.append(", accountNonLocked=");
		sb.append(accountNonLocked);
		sb.append(", credentialsNonExpired=");
		sb.append(credentialsNonExpired);

		return sb.toString();
	}
}
