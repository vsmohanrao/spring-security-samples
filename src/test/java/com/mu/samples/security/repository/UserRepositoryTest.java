package com.mu.samples.security.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mu.samples.security.model.UserModel;

public class UserRepositoryTest {

	@Autowired
	private UserRepository userRepository;

	@Before
	public void init() {
		userRepository = mock(UserRepository.class);
	}

	@Test
	public void testFindByUserName() {
		UserModel userModel = new UserModel();
		when(userRepository.findByUserName("mohanu")).thenReturn(userModel);
		UserModel actualUserModel = userRepository.findByUserName("mohanu");
		assertEquals(userModel, actualUserModel);
	}

}
