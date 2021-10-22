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

import com.sapient.trg.entity.TaxationMaster;
import com.sapient.trg.entity.UserCredential;
import com.sapient.trg.service.TaxationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/*api End Points-
 * http://localhost:8082/usm/taxation/addTaxation
 * http://localhost:8082/usm/taxation/all
 * http://localhost:8082/usm/taxation/<taxation_id>
 * http://localhost:8082/usm/taxation/delete/<Taxation_id>
 */
	 
@Api
@RestController
@Slf4j
@RequestMapping("/taxation")
//permitting cross-origin requests 
@CrossOrigin
public class TaxationMasterController {

	
	@Autowired
	private TaxationService taxationService;
	
	@ApiOperation(value = "add new Taxation",
			consumes = "TaxationMaster",
			produces = "Taxation id",
			response = TaxationMaster.class,
			nickname = "Add Taxation",
			notes = "http://localhost:8082/usm/taxation/addTaxation"
			)
	
	@PostMapping("/addTaxation")
	public ResponseEntity<Long> addTaxation(@RequestBody TaxationMaster taxation){
		try {
			Long Taxation = taxationService.addNewTaxation(taxation);
			log.info("New Taxation added");
			return new ResponseEntity<>(Taxation,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@ApiOperation(value = "Find All Taxation",
			consumes = "none",
			produces = "List of Taxation objects",
			response = TaxationMaster.class,
			nickname = "getAllTaxation",
			notes = "http://localhost:8082/usm/taxation/all"
			)
	@GetMapping("/all")
	public ResponseEntity<List<TaxationMaster>> getAllTaxations(){
		try {
			List<TaxationMaster> taxationList= taxationService.getAllTaxation();
			if(taxationList.size()==0) {
				log.info("No Taxation in database");
				throw new Exception("No Taxation in database");
			}
			return new ResponseEntity<>(taxationList,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(consumes = "taxation_id",produces = "TaxationMaster",
			notes = "http://localhost:8082/usm/taxation/<Tax_id>",
			value = "Get tax by tax_id",
			response = TaxationMaster.class,
			nickname = "get-Taxation By Taxation-id" )
	@GetMapping("/taxation/{id}")
	public ResponseEntity<TaxationMaster> getTaxationById(@PathVariable(name = "id")Long Id){
		try {
			TaxationMaster taxation= taxationService.getTaxation(Id);
			log.info("returns tax :"+Id+" details");
			return new ResponseEntity<>(taxation,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@ApiOperation(consumes = "Taxation_id",produces = "Taxation_id",
			notes = "http://localhost:8082/usm/taxation/delete/<Taxation_id>",
			value = "Delete Taxation",
			response = Long.class,
			nickname = "delete-Taxation" )
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteTaxation(@PathVariable(name = "id") Long Id){
		try {
			Long deletedTaxation= taxationService.deleteTaxation(Id);
			log.info("Deleted Taxation:"+ deletedTaxation);
			return new ResponseEntity<>(deletedTaxation,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	
}
}
