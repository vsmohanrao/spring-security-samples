package com.mu.samples.security.service.impl;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mu.samples.security.model.UserModel;
import com.mu.samples.security.repository.UserRepository;

@Service("userDetailsService")
public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(CustomUserDetailsService.class);

	@Autowired
	private UserRepository userRepository;

	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {

		UserModel userModel = userRepository.findByUserName(userName);
	
		LOG.debug("User Model {}", userModel);
	
		return new CustomUserDetails(userModel);
	}

	private static class CustomUserDetails extends UserModel implements
			UserDetails {

		private static final long serialVersionUID = -4018524114079961667L;

		public CustomUserDetails(UserModel userModel) {
			super(userModel);
		}

		public Collection<? extends GrantedAuthority> getAuthorities() {
			return null;
		}

		public String getUsername() {
			return super.getPassword();
		}

		public boolean isAccountNonExpired() {
			return super.getAccountNonExpired() == 1 ? true : false;
		}

		public boolean isAccountNonLocked() {
			return super.getAccountNonLocked() == 1 ? true : false;
		}

		public boolean isCredentialsNonExpired() {
			return super.getCredentialsNonExpired() == 1 ? true : false;
		}

		public boolean isEnabled() {
			return super.getEnabled() == 1 ? true : false;
		}

	}

}
