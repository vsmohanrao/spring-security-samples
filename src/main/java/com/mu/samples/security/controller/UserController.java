package com.mu.samples.security.controller;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mu.samples.security.dto.UserDTO;

@RestController
public class UserController {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserController.class);

	@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<?> getUsers() {
		LOG.debug("Users returned");
		return Collections.emptyList();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void createUser(@RequestBody UserDTO user) {
		LOG.debug("User Created ");
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
	public void deleteUser(@PathVariable Integer id) {
		LOG.debug("User deleted ");
	}

}
