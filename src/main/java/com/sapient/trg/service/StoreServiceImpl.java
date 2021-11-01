package com.sapient.trg.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sapient.trg.dao.StoreRepository;
import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.entity.StoreMaster;
import com.sapient.trg.exception.StoreException;

@Service
@Transactional

public class StoreServiceImpl implements StoreService {
	
	@Autowired
	private StoreRepository storedao;

	@Override
	public Long addNewStore(StoreMaster Store) throws StoreException {
		try {
			StoreMaster savedStore = storedao.save(Store);
			return savedStore.getStoreID();
		}catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}catch(Exception e) {
			throw new StoreException(e.getMessage(),e);
		}
	}

	@Override
	public StoreMaster getStore(Long store_id) throws StoreException {
		try {
			Optional<StoreMaster> optional=storedao.findById(store_id);
			if(optional.isPresent()) {				
				return optional.get();
			}else {
				throw new Exception("Invalid Store_id");
			}
		}catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}catch(Exception e) {
			throw new StoreException(e.getMessage(),e);
		}
	}

	@Override
	public List<StoreMaster> getAllStores() throws StoreException {
		try {
			List<StoreMaster> storeList= storedao.findAll();
			if(storeList.size()!= 0) {
				return storeList;
			}else {
				throw new Exception("No store in the database");
			}
		}catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}catch(Exception e) {
			throw new StoreException(e.getMessage(),e);
		}
	}

	@Override
	public Long deleteStore(Long store_id) throws StoreException {
		try {
			storedao.deleteById(store_id);
			return store_id;
		}catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}catch(Exception e) {
			throw new StoreException(e.getMessage(),e);
		}
	}
//
	@Override
	public List<StoreMaster> getStoresbyregion(RegionMaster regionID) throws StoreException {
		try {
			List<StoreMaster> storeList=storedao.getStoreMasterByRegion(regionID);
			if(storeList.size()!= 0) {
				return storeList;
			}else {
				throw new Exception("No store in the database");
				}
			}catch(DataAccessException e) {
			throw new StoreException(e.getMessage(),e);
		}catch(Exception e) {
			throw new StoreException(e.getMessage(),e);
		}
	}
//	

}
