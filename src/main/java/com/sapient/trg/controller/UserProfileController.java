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

import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.entity.StoreMaster;
import com.sapient.trg.entity.UserCredential;
import com.sapient.trg.entity.UserProfile;
import com.sapient.trg.service.RegionService;
import com.sapient.trg.service.StoreService;
import com.sapient.trg.service.UserProfileService;
import com.sapient.trg.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;



/*Api End Points-
* http://localhost:8082/usm/Userprofile/add   --------To Add New User
* http://localhost:8082/usm/Userprofile/all  ---------------To Find all User Credential
* http://localhost:8082/usm/Userprofile/<User_id>    -------To Find By User_id
* http://localhost:8082/usm/Userprofile/delete/<User_id>         ------ To Delete By User_id 
* http://localhost:8082/usm/Userprofile/update                     ------TO update
*/

@Api
@RestController
@Slf4j
@RequestMapping("/Userprofile")
//permitting cross-origin requests 
@CrossOrigin

public class UserProfileController {
	
	@Autowired
	private UserProfileService userProfile;
	
	@Autowired
	private StoreService storeMaster;
	
	@Autowired
	private RegionService regionService;
	
	@ApiOperation(value = "add new Profile",
			consumes = "UserProfile",
			produces = "User Id",
			response = UserProfile.class,
			nickname = "Add User Profile",
			notes = "http://localhost:8082/usm/Userprofile/add"
			)
	@PostMapping("/add")
	public ResponseEntity<Long> addUser(@RequestBody UserProfile role){
		try {
			Long user = userProfile.addNewUser(role);
			log.info("New user added");
			return new ResponseEntity<>(user,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(value = "Find All Users Profile",
			consumes = "none",
			produces = "List of UserProfile objects",
			response = UserProfile.class,
			nickname = "getAllUsersProfile",
			notes = "http://localhost:8082/usm/Userprofile/all"
			)
	@GetMapping("/all")
	public ResponseEntity<List<UserProfile>> getAllUsers(){
		try {
			List<UserProfile> userList= userProfile.getAllUsers();
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
	
	@ApiOperation(consumes = "User_id",produces = "UserProfile",
			notes = "http://localhost:8082/usm/Userprofile/profile/<User_id>",
			value = "Get User Profile by User_id",
			response = UserProfile.class,
			nickname = "get-User-Profile" )
	@GetMapping("/profile/{id}")
	public ResponseEntity<UserProfile> getprofileById(@PathVariable(name = "id")Long Id){
		try {
			UserProfile user= userProfile.getUserDetails(Id);
			log.info("returns user :"+Id+" details");
			return new ResponseEntity<>(user,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(consumes = "User_id",produces = "User_id",
			notes = "http://localhost:8082/usm/Userprofile/delete/<User_id>",
			value = "Delete User Profile",
			response = Long.class,
			nickname = "delete-User-Profile" )
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteProfile(@PathVariable(name = "id") Long Id){
		try {
			Long deleteduser= userProfile.deleteUser(Id);
			log.info("Deleted User:"+ deleteduser);
			return new ResponseEntity<>(deleteduser,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
}
	
	
	@ApiOperation(consumes = "UserProfile",produces = "UserProfile",
			notes = "http://localhost:8082/usm/Userprofile/update",
			value = "Update User Profile",
			response = UserProfile.class,
			nickname = "update-User-Profile" )
	@PutMapping("/update")
	public ResponseEntity<UserProfile> updateProfile(@RequestBody UserProfile user){
		try {
			UserProfile updatedProfile = userProfile.updateUserDetails(user);
			log.info("User Profile updated");
			return new ResponseEntity<>(updatedProfile,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}

	@ApiOperation(value = "Find All Users Profile By Store",
			consumes = "Store Id",
			produces = "List of UserProfile objects",
			response = UserProfile.class,
			nickname = "getAllUsersProfileBy StoreId",
			notes = "http://localhost:8082/usm/Userprofile/store/all"
			)
	@GetMapping("/store/{id}")
	public ResponseEntity<List<UserProfile>> getAllUsersByStore(@PathVariable(name = "id")Long Id){
		try {
			StoreMaster region=storeMaster.getStore(Id);
			List<UserProfile> userList= userProfile.getAllUsersByStoreId(region);
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
	
	@ApiOperation(value = "Find All Users Profile By region",
			consumes = "Region Id",
			produces = "List of UserProfile objects",
			response = UserProfile.class,
			nickname = "getAllUsersProfileBy regionId",
			notes = "http://localhost:8082/usm/Userprofile/region/{id}"
			)
	@GetMapping("/region/{id}")
	public ResponseEntity<List<UserProfile>> getAllUsersByRegion(@PathVariable(name = "id")Long Id){
		try {
			RegionMaster region=regionService.getRegion(Id);
			List<UserProfile> userList= userProfile.getAllUsersByregionId(region);
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
}
