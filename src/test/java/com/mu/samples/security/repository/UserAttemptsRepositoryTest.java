package com.mu.samples.security.repository;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Calendar;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.mu.samples.security.model.UserAttemptsModel;

public class UserAttemptsRepositoryTest {

	@Autowired
	private UserAttemptsRepository userAttemptsRepository;

	@Before
	public void init() {
		userAttemptsRepository = mock(UserAttemptsRepository.class);
	}

	@Test
	public void testUpdateUserFailAttempts() {
		Date now = Calendar.getInstance().getTime();

		when(userAttemptsRepository.updateUserFailedAttempts("mohanu", now))
				.thenReturn(1);
		int updateStatus = userAttemptsRepository.updateUserFailedAttempts(
				"mohanu", now);
		assertEquals(1, updateStatus);
	}

	@Test
	public void testFindByUserName() {
		UserAttemptsModel userAttemptsModel = new UserAttemptsModel();
		when(userAttemptsRepository.findByUserName("mohanu")).thenReturn(
				userAttemptsModel);
		UserAttemptsModel actualUserAttemptsModel = userAttemptsRepository
				.findByUserName("mohanu");
		assertEquals(userAttemptsModel, actualUserAttemptsModel);
	}

	@Test
	public void testResetFailAttempts() {
		Date now = Calendar.getInstance().getTime();
		when(userAttemptsRepository.resetFailAttempts("mohanu", now))
				.thenReturn(1);
		int updateStatus = userAttemptsRepository.resetFailAttempts("mohanu",
				now);
		assertEquals(1, updateStatus);
	}

}
