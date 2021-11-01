package com.sapient.trg.mockproject;

import java.util.List;
import java.util.Optional;

import javax.persistence.Access;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.sapient.trg.dao.StoreRepository;
import com.sapient.trg.entity.StoreMaster;
import com.sapient.trg.service.StoreService;

@SpringBootTest
class MockProjectApplicationTests {
	
	
 @Autowired
 private StoreRepository storeRepository;
 
 @Test
 public void printgetStoreMasterByRegion() {
	 StoreMaster store=storeRepository.getStoreMasterByRegion(59l);
	 System.out.println(store);
 }
 

	
	
}
