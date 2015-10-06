package com.mu.samples.security.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mu.samples.security.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String> {

	UserModel findByUserName(String userName);

}
