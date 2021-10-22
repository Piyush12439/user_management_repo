package com.sapient.trg.service;


import java.util.List;


import com.sapient.trg.entity.UserProfile;


import com.sapient.trg.exception.UserProfileException;

public interface UserProfileService {
	public abstract Long addNewUser(UserProfile User) throws UserProfileException;
	public abstract UserProfile getUserDetails(Long User_id) throws UserProfileException;
	public abstract List<UserProfile> getAllUsers() throws UserProfileException;
	public abstract Long deleteUser(Long Userid) throws UserProfileException;
	public abstract UserProfile updateUserDetails(UserProfile User) throws UserProfileException;


}
