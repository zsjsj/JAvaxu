package com.oldboy.sm.service;

import com.oldboy.sm.domain.User;

/**
 * userService
 */
public interface UserService {
	public void insertUser(User user);

	public void delete(Integer id) ;

	public void selectUser(Integer id);

	public void updateUser(User user);
}
