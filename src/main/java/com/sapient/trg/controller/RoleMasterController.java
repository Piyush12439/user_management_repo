package com.sapient.trg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.sapient.trg.entity.RoleMaster;
import com.sapient.trg.entity.UserCredential;
import com.sapient.trg.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/*api End Points-
 * http://localhost:8082/usm/role/addRole
 * http://localhost:8082/usm/role/all
 * http://localhost:8082/usm/role/<User_id>
 * http://localhost:8082/usm/role/delete/<Role_id>
 */
	 
@Api
@RestController
@Slf4j
@RequestMapping("/role")
//permitting cross-origin requests 
@CrossOrigin
public class RoleMasterController {

	
	@Autowired
	private RoleService roleService;
	
	@ApiOperation(value = "Add new role",
			consumes = "RoleMaster",
			produces = "Role id",
			response = RoleMaster.class,
			nickname = "Add Role",
			notes = "http://localhost:8082/usm/role/addRole"
			)
	@PostMapping("/addRole")
	public ResponseEntity<Long> addRole(@RequestBody RoleMaster role){
		try {
			Long Role = roleService.addNewRole(role);
			log.info("New Role added");
			return new ResponseEntity<>(Role,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@ApiOperation(value = "Find All Role",
			consumes = "none",
			produces = "List of Role objects",
			response = RoleMaster.class,
			nickname = "getAllRole",
			notes = "http://localhost:8082/usm/role/all"
			)
	@GetMapping("/all")
	public ResponseEntity<List<RoleMaster>> getAllRoles(){
		try {
			List<RoleMaster> roleList= roleService.getAllRole();
			if(roleList.size()==0) {
				log.info("No Role in database");
				throw new Exception("No Role in database");
			}
			return new ResponseEntity<>(roleList,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(consumes = "role_id",produces = "RoleMaster",
			notes = "http://localhost:8082/usm/role/<User_id>",
			value = "Get Role by role_id",
			response = RoleMaster.class,
			nickname = "get-Role By Role-id" )
	@GetMapping("/role/{id}")
	public ResponseEntity<RoleMaster> getRoleById(@PathVariable(name = "id")Long Id){
		try {
			RoleMaster role= roleService.getRole(Id);
			log.info("returns role :"+Id+" details");
			return new ResponseEntity<>(role,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@ApiOperation(consumes = "Role_id",produces = "Role_id",
			notes = "http://localhost:8082/usm/role/delete/<Role_id>",
			value = "Delete Role",
			response = Long.class,
			nickname = "delete-Role" )
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteRole(@PathVariable(name = "id") Long Id){
		try {
			Long deletedRole= roleService.deleteRole(Id);
			log.info("Deleted Role:"+ deletedRole);
			return new ResponseEntity<>(deletedRole,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	
}
}
