package com.sapient.trg.service;

import java.util.List;

import com.sapient.trg.entity.UserCredential;

import com.sapient.trg.exception.UserProfileException;

public interface UserService {
	public abstract Long addNewUser(UserCredential User) throws UserProfileException;
	public abstract UserCredential getUserDetails(Long User_id) throws UserProfileException;
	public abstract List<UserCredential> getAllUsers() throws UserProfileException;
	public abstract Long deleteUser(Long Userid) throws UserProfileException;
	public abstract UserCredential updateUserDetails(UserCredential User) throws UserProfileException;

	

}
