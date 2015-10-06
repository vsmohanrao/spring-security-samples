package com.mu.samples.security.repository;

import java.util.Date;

public interface UserAttemptsCustomRepository {

	int updateUserFailedAttempts(String userName, Date lastModified);

}
