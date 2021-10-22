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

import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.entity.UserCredential;
import com.sapient.trg.service.RegionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/*api End Points-
 * http://localhost:8082/usn/region/addRegion
 * http://localhost:8082/usm/region/all
 * http://localhost:8082/usm/region/<region_id>
 * http://localhost:8082/usm/region/delete/<Region_id>
 */
	 
@Api
@RestController
@Slf4j
@RequestMapping("/region")
//permitting cross-origin requests 
@CrossOrigin
public class RegionMasterController {

	@Autowired
	private RegionService regionService;
	
	//http://localhost:8082/usm/region/addRegion
	@ApiOperation(value = "add new Region",
			consumes = "RegionMaster",
			produces = "Region id",
			response = RegionMaster.class,
			nickname = "Add Region",
			notes = "http://localhost:8082/usm/region/addRegion"
			)
	@PostMapping("/addRegion")
	public ResponseEntity<Long> addRegion(@RequestBody RegionMaster region){
		try {
			Long Region = regionService.addNewRegion(region);
			log.info("New Region added");
			return new ResponseEntity<>(Region,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@ApiOperation(consumes = "region_id",
			produces = "RegionMaster",
			notes = "http://localhost:8082/usm/region/all",
			value = "Get All Region",						
			response = RegionMaster.class,
			nickname = "Get All Region"
			)
	@GetMapping("/all")
	public ResponseEntity<List<RegionMaster>> getAllRegions(){
		try {
			List<RegionMaster> regionList= regionService.getAllRegion();
			if(regionList.size()==0) {
				log.info("No Region in database");
				throw new Exception("No Region in database");
			}
			return new ResponseEntity<>(regionList,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(consumes = "region_id",produces = "RegionMaster",
			notes = "http://localhost:8082/usm/region/<region_id>",
			value = "Get region by region_id",
			response = RegionMaster.class,
			nickname = "get-Region By Region-id" )
	@GetMapping("/{id}")
	public ResponseEntity<RegionMaster> getRegionById(@PathVariable(name = "id")Long Id){
		try {
			RegionMaster region= regionService.getRegion(Id);
			log.info("returns region :"+Id+" details");
			return new ResponseEntity<>(region,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@ApiOperation(consumes = "Region_id",produces = "Region_id",
			notes = "http://localhost:8082/usm/region/delete/<Region_id>",
			value = "Delete Region",
			response = Long.class,
			nickname = "delete-Region" )
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteRegion(@PathVariable(name = "id") Long Id){
		try {
			Long deletedRegion= regionService.deleteRegion(Id);
			log.info("Deleted Region:"+ deletedRegion);
			return new ResponseEntity<>(deletedRegion,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	
}
}
