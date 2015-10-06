package com.mu.samples.security.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mu.samples.security.model.UserAttemptsModel;

@Repository
public interface UserAttemptsRepository extends
		CrudRepository<UserAttemptsModel, String>, UserAttemptsCustomRepository {

	UserAttemptsModel findByUserName(String userName);

	@Transactional(timeout = 10)
	@Modifying(clearAutomatically = true)
	@Query("UPDATE UserAttemptsModel ua SET ua.attempts = 0, ua.lastModified = :lastModifiedDate  where ua.userName = :userName")
	int resetFailAttempts(@Param("userName") String userName,
			@Param("lastModifiedDate") Date lastModifiedDate);

}
