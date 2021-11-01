package com.sapient.trg.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.entity.RoleMaster;
import com.sapient.trg.entity.StoreMaster;

@Repository
public interface StoreRepository extends JpaRepository<StoreMaster,Long>{
	
	
	@Query ("select s from StoreMaster s where s.region= ?1")
	List<StoreMaster> getStoreMasterByRegion(RegionMaster storeID);
}