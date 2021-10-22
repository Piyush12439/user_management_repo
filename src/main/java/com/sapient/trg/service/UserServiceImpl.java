package com.sapient.trg.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sapient.trg.dao.UserCredentialRepository;
import com.sapient.trg.entity.UserCredential;
import com.sapient.trg.exception.UserProfileException;

@Service
@Transactional

public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserCredentialRepository userRepo;

	@Override
	public Long addNewUser(UserCredential User) throws UserProfileException {
		try {
			UserCredential saveduser = userRepo.save(User);
			return saveduser.getUserId();
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public UserCredential getUserDetails(Long User_id) throws UserProfileException {
		try {
			Optional<UserCredential> optional=userRepo.findById(User_id);
			if(optional.isPresent()) {				
				return optional.get();
			}else {
				throw new Exception("Invalid UserId");
			}
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public List<UserCredential> getAllUsers() throws UserProfileException {
		try {
			List<UserCredential> userList= userRepo.findAll();
			if(userList.size()!= 0) {
				return userList;
			}else {
				throw new Exception("No Users in the database");
			}
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public Long deleteUser(Long Userid) throws UserProfileException {
		try {
			userRepo.deleteById(Userid);
			return Userid;
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public UserCredential updateUserDetails(UserCredential User) throws UserProfileException {
		try {
			UserCredential updateduser=userRepo.save(User);
			return updateduser;
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	

}
