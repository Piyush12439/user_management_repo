package com.sapient.trg.service;

import java.util.List;
import com.sapient.trg.entity.RoleMaster;


import com.sapient.trg.exception.UserProfileException;

public interface RoleService {
	public abstract Long addNewRole(RoleMaster Role) throws UserProfileException;
	public abstract RoleMaster getRole(Long role_id) throws UserProfileException;
	public abstract List<RoleMaster> getAllRole() throws UserProfileException;
	public abstract Long deleteRole(Long role_id) throws UserProfileException;
	

}
