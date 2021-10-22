package com.sapient.trg.service;
import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sapient.trg.dao.UserProfileRepository;
import com.sapient.trg.entity.UserProfile;
import com.sapient.trg.exception.UserProfileException;

@Service
@Transactional


public class UserProfileImpl implements UserProfileService{
	
	@Autowired
	private UserProfileRepository userProfileRepo;

	@Override
	public Long addNewUser(UserProfile User) throws UserProfileException {
		try {
			UserProfile saveduser = userProfileRepo.save(User);
			return saveduser.getId();
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public UserProfile getUserDetails(Long User_id) throws UserProfileException {
		try {
			Optional<UserProfile> optional=userProfileRepo.findById(User_id);
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
	public List<UserProfile> getAllUsers() throws UserProfileException {
		try {
			List<UserProfile> userList= userProfileRepo.findAll();
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
			userProfileRepo.deleteById(Userid);
			return Userid;
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public UserProfile updateUserDetails(UserProfile User) throws UserProfileException {
		try {
			UserProfile updateduser=userProfileRepo.save(User);
			return updateduser;
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}


}
