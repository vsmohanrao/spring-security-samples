package com.mu.samples.security.repository.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import com.mu.samples.security.model.UserAttemptsModel;
import com.mu.samples.security.repository.UserAttemptsCustomRepository;

public class UserAttemptsRepositoryImpl implements UserAttemptsCustomRepository {

	private static final Logger LOG = LoggerFactory
			.getLogger(UserAttemptsRepositoryImpl.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static final String UPDATE_USER_FAIL_ATTEMPTS = "update user_attempts set attempts = attempts + 1, last_modified = ? where username = ?;";

	private static final String SELECT_USER_ATTEMPTS = "select * from user_attempts ua where ua.username = ?";

	private static final String UPDATE_USER_WITH_LOCKED_STATUS = "update users set account_non_locked = 0 where username = ?";

	private static final int MAX_FAIL_ATTEMPTS = 3;

	@Override
	@Transactional(timeout = 20)
	public int updateUserFailedAttempts(String userName, Date lastModified) {

		int isUpdated = updateFailAttempts(userName, lastModified);

		UserAttemptsModel userAttemptsModel = jdbcTemplate.queryForObject(
				SELECT_USER_ATTEMPTS, new String[] { userName },
				new RowMapper<UserAttemptsModel>() {

					@Override
					public UserAttemptsModel mapRow(ResultSet rs, int rowNum)
							throws SQLException {

						UserAttemptsModel tmpUsrAtmptsModel = new UserAttemptsModel();

						tmpUsrAtmptsModel.setUserName(rs.getString(1));
						tmpUsrAtmptsModel.setAttempts(rs.getInt(2));
						tmpUsrAtmptsModel.setLastModified(new Date(rs
								.getDate(3).getTime()));

						return tmpUsrAtmptsModel;
					}
				});

		if (userAttemptsModel.getAttempts() >= MAX_FAIL_ATTEMPTS) {
			updateUserLockStatus(userName);
		}

		LOG.debug("isUpdated : {}", isUpdated);
		return isUpdated;

	}

	private Integer updateFailAttempts(final String userName,
			final Date lastModified) {
		return jdbcTemplate.execute(UPDATE_USER_FAIL_ATTEMPTS,
				new PreparedStatementCallback<Integer>() {

					@Override
					public Integer doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						ps.setDate(1, new java.sql.Date(lastModified.getTime()));
						ps.setString(2, userName);
						return ps.executeUpdate();
					}

				});
	}

	private Integer updateUserLockStatus(final String userName) {
		return jdbcTemplate.execute(UPDATE_USER_WITH_LOCKED_STATUS,
				new PreparedStatementCallback<Integer>() {

					@Override
					public Integer doInPreparedStatement(PreparedStatement ps)
							throws SQLException, DataAccessException {
						ps.setString(1, userName);
						return ps.executeUpdate();
					}

				});
	}
}
