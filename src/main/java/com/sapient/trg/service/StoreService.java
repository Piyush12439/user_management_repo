package com.sapient.trg.service;

import java.util.List;

import com.sapient.trg.entity.StoreMaster;

import com.sapient.trg.exception.StoreException;

public interface StoreService {
	public abstract Long addNewStore(StoreMaster Store) throws StoreException;
	public abstract StoreMaster getStore(Long storeID) throws StoreException;
	public abstract List<StoreMaster> getAllStores() throws StoreException;
	public abstract Long deleteStore(Long storeID) throws StoreException;
//	public abstract StoreMaster getStoreregion(Long regionID) throws StoreException;

}
