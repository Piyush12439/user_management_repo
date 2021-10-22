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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


import com.sapient.trg.entity.UserCredential;
import com.sapient.trg.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



/*Api End Points-
 * http://localhost:8082/usm/Credential/add   --------To Add New User
 * http://localhost:8082/usm/Credential/all  ---------------To Find all User Credential
 * http://localhost:8082/usm/Credential/<User_id>    -------To Find By User_id
 * http://localhost:8082/usm/Credential/delete/<User_id>         ------ To Delete By User_id 
 * http://localhost:8082/usm/Credential/update                  ------TO update
 */

@Api
@RestController
@Slf4j
@RequestMapping("/Credential")
//permitting cross-origin requests 
@CrossOrigin
public class UserCredentialController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "add new Credential",
			consumes = "UserCredential",
			produces = "User Id",
			response = UserCredential.class,
			nickname = "Add User Credential",
			notes = "http://localhost:8082/usm/Credential/add"
			)
	@PostMapping("/add")
	public ResponseEntity<Long> addUser(@RequestBody UserCredential role){
		try {
			Long user = userService.addNewUser(role);
			log.info("New user added");
			return new ResponseEntity<>(user,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(value = "Find All Users Credential",
			consumes = "none",
			produces = "List of User Credential objects",
			response = UserCredential.class,
			nickname = "getAllUsersCredential",
			notes = "http://localhost:8082/usm/Credential/all"
			)
	@GetMapping("/all")
	public ResponseEntity<List<UserCredential>> getAllUsers(){
		try {
			List<UserCredential> userList= userService.getAllUsers();
			if(userList.size()==0) {
				log.info("No User in database");
				throw new Exception("No User in database");
			}
			return new ResponseEntity<>(userList,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	@ApiOperation(consumes = "User_id",produces = "UserCredential",
			notes = "http://localhost:8082/usm/Credential/<User_id>",
			value = "Get User Password by User_id",
			response = UserCredential.class,
			nickname = "get-User-Credential" )
	@GetMapping("/{id}")
	public ResponseEntity<String> getCredentialById(@PathVariable(name = "id")Long Id){
		try {
			UserCredential user= userService.getUserDetails(Id);
			String pass=user.getPassword();
			System.out.println(pass);
			log.info("returns user :"+Id+" details");
			return new ResponseEntity<>(pass,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(consumes = "User_id",produces = "User_id",
			notes = "http://localhost:8082/usm/Credential/delete/<User_id>",
			value = "Delete User Credential",
			response = Long.class,
			nickname = "delete-User-Credential" )
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteCredentail(@PathVariable(name = "id") Long Id){
		try {
			Long deleteduser= userService.deleteUser(Id);
			log.info("Deleted User:"+ deleteduser);
			return new ResponseEntity<>(deleteduser,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
}
	
	
	@ApiOperation(consumes = "UserCredential",produces = "UserCredential",
			notes = "http://localhost:8082/usm/Credential/update",
			value = "Update User Credential",
			response = UserCredential.class,
			nickname = "update-User-Credential" )
	@PutMapping("/update")
	public ResponseEntity<UserCredential> updateCredential(@RequestBody UserCredential user){
		try {
			UserCredential updatedCredential = userService.updateUserDetails(user);
			log.info("User Credential updated");
			return new ResponseEntity<>(updatedCredential,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}