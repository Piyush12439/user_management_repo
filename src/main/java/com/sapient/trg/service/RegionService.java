package com.sapient.trg.service;

import java.util.List;

import com.sapient.trg.entity.RegionMaster;

import com.sapient.trg.exception.RegionException;

public interface RegionService {
	public abstract Long addNewRegion(RegionMaster Region) throws RegionException;
	public abstract RegionMaster getRegion(Long regionID) throws RegionException;
	public abstract List<RegionMaster> getAllRegion() throws RegionException;
	public abstract Long deleteRegion(Long regionID) throws RegionException;
	

}
