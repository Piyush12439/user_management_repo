package com.sapient.trg.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sapient.trg.dao.RoleRepository;
import com.sapient.trg.entity.RoleMaster;
import com.sapient.trg.exception.UserProfileException;

@Service
@Transactional

public class RoleServiceImpl implements RoleService {
	
	@Autowired
	private RoleRepository roledao;

	@Override
	public Long addNewRole(RoleMaster Role) throws UserProfileException {
		try {
			RoleMaster savedRole = roledao.save(Role);
			return savedRole.getRoleId();
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public RoleMaster getRole(Long role_id) throws UserProfileException {
		try {
			Optional<RoleMaster> optional=roledao.findById(role_id);
			if(optional.isPresent()) {				
				return optional.get();
			}else {
				throw new Exception("Invalid Role_id");
			}
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public List<RoleMaster> getAllRole() throws UserProfileException {
		try {
			List<RoleMaster> roleList= roledao.findAll();
			if(roleList.size()!= 0) {
				return roleList;
			}else {
				throw new Exception("No role in the database");
			}
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}

	@Override
	public Long deleteRole(Long role_id) throws UserProfileException {
		try {
			roledao.deleteById(role_id);
			return role_id;
		}catch(DataAccessException e) {
			throw new UserProfileException(e.getMessage(),e);
		}catch(Exception e) {
			throw new UserProfileException(e.getMessage(),e);
		}
	}
	

}
