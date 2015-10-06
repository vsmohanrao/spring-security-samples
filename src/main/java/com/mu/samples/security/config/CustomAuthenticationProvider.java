package com.mu.samples.security.config;

import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import com.mu.samples.security.repository.UserAttemptsRepository;

@Component("authenticationProvider")
public class CustomAuthenticationProvider extends DaoAuthenticationProvider {

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomAuthenticationProvider.class);

	@Autowired
	private UserAttemptsRepository userAttemptsRepository;

	@Autowired
	@Qualifier("userDetailsService")
	@Override
	public void setUserDetailsService(UserDetailsService userDetailsService) {
		super.setUserDetailsService(userDetailsService);
	}

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		Authentication auth = null;
		LOG.debug("User name {}", authentication.getName());
		try {
			auth = super.authenticate(authentication);

			userAttemptsRepository.resetFailAttempts(authentication.getName(),
					Calendar.getInstance().getTime());

		} catch (BadCredentialsException badCredentialsException) {
			userAttemptsRepository.updateUserFailedAttempts(
					authentication.getName(), Calendar.getInstance().getTime());
			LOG.error(badCredentialsException.getMessage(),
					badCredentialsException);
			throw badCredentialsException;
		} catch (LockedException lockedException) {
			LOG.error(lockedException.getMessage(), lockedException);
			throw new LockedException("User Locked!!!");
		}

		return auth;
	}
}
