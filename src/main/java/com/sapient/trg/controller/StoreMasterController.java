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

import com.sapient.trg.dao.RegionRepository;
import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.entity.StoreMaster;
import com.sapient.trg.service.RegionService;
import com.sapient.trg.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


/*api End Points-
 * http://localhost:8082/usm/store/addStore
 * http://localhost:8082/usm/store/all
 * http://localhost:8082/usm/store/<store_id>
 * http://localhost:8082/usm/store/delete/<Store_id>
 */
	 
@Api
@RestController
@Slf4j
@RequestMapping("/store")
//permitting cross-origin requests 
@CrossOrigin
public class StoreMasterController {

	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private RegionService regionMaster;
	
//	//http://localhost:8082/ums/store/all
//	@GetMapping("/all")
//	public List<StoreMaster> getStore() throws StoreException{
//		return storeService.getAllStore();
//	}
	
	@ApiOperation(value = "Find All Store",
			consumes = "none",
			produces = "List of Store objects",
			response = StoreMaster.class,
			nickname = "getAllStore",
			notes = "http://localhost:8082/usm/store/all"
			)
	@GetMapping("/all")
	public ResponseEntity<List<StoreMaster>> getAllStores(){
		try {
			List<StoreMaster> storeList= storeService.getAllStores();
//			System.out.println(storeList);
			if(storeList.size()==0) {
				log.info("No Store in database");
				throw new Exception("No Store in database");
			}
			return new ResponseEntity<>(storeList,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	@ApiOperation(value = "Add new Store",
			consumes = "StoreMaster",
			produces = "Store id",
			response = StoreMaster.class,
			nickname = "Add Store",
			notes = "http://localhost:8082/usm/store/addStore"
			)
	
	@PostMapping("/addStore")
	public ResponseEntity<Long> addStore(@RequestBody StoreMaster store){
		try {
			Long Store = storeService.addNewStore(store);
			log.info("New Store added");
			return new ResponseEntity<>(Store,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	
	
	@ApiOperation(consumes = "store_id",produces = "StoreMaster",
			notes = "http://localhost:8082/usm/store/<Store_id>",
			value = "Get store by store_id",
			response = StoreMaster.class,
			nickname = "get-Store By Store-id" )
	@GetMapping("/store/{id}")
	public ResponseEntity<StoreMaster> getStoreById(@PathVariable(name = "id")Long Id){
		try {
			StoreMaster store= storeService.getStore(Id);
			log.info("returns store :"+Id+" details");
			return new ResponseEntity<>(store,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
	
	
	@ApiOperation(consumes = "Store_id",produces = "Store_id",
			notes = "http://localhost:8082/usm/store/delete/<Store_id>",
			value = "Delete Store",
			response = Long.class,
			nickname = "delete-Store" )
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Long> deleteStore(@PathVariable(name = "id") Long Id){
		try {
			Long deletedStore= storeService.deleteStore(Id);
			log.info("Deleted Store:"+ deletedStore);
			return new ResponseEntity<>(deletedStore,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	
}
	@ApiOperation(consumes = "region_id",produces = "StoreMaster",
			notes = "http://localhost:8082/usm/store/region/{id}",
			value = "Get stores in a region by region_id",
			response = RegionMaster.class,
			nickname = "get-Stores By region-id" )
	@GetMapping("/region/{id}")
	public ResponseEntity<List<StoreMaster>> getStoreByRegionId(@PathVariable(name = "id")Long Id){
		try {
			RegionMaster region=regionMaster.getRegion(Id);
			List<StoreMaster> store= storeService.getStoresbyregion(region);
			log.info("returns store :"+Id+" details");
			return new ResponseEntity<>(store,HttpStatus.OK);
		}catch(Exception e) {
			log.error(e.getMessage(),e);
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,e.getMessage());
		}
	}
}
