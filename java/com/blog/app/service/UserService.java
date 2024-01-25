package com.blog.app.service;

import java.util.List;

import com.blog.app.dao.User;
import com.blog.app.payload.UserDto;


public interface UserService {

	/**
	 * This method will help to add the new user
	 * @param UserDto -- contains user details
	 * @return UserDto -- will return new added user
	 */
	public UserDto addUser(final UserDto user);
	
	/**
	 * This method will help to update user
	 * @param UserDto -- contains user details
	 * @param userId -- contains user id
	 * @return UserDto -- will return new added user
	 */
	public UserDto updateuser(final UserDto User, final Integer userId);
	
	/**
	 * This method use to get single user by id
	 * @param id -- contains user id
	 * @return UserDto -- will return user details
	 */
	public UserDto getUserById(final Integer id);
	
	/**
	 * This method use to get all users list
	 * @return -- will return users list
	 */
	public List<UserDto> getAllUsers();
	
	/**
	 * This method use to delete the given user
	 * @param id -- contains id
	 */
	public void deleteUser(final Integer id);
}
