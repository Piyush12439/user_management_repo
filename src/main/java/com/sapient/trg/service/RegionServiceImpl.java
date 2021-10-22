package com.sapient.trg.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sapient.trg.dao.RegionRepository;
import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.exception.RegionException;

@Service
@Transactional

public class RegionServiceImpl implements RegionService {
	
	@Autowired
	private RegionRepository regiondao;

	@Override
	public Long addNewRegion(RegionMaster Region) throws RegionException {
		try {
			RegionMaster savedRegion = regiondao.save(Region);
			return savedRegion.getRegionID();
		}catch(DataAccessException e) {
			throw new RegionException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegionException(e.getMessage(),e);
		}
	}

	@Override
	public RegionMaster getRegion(Long region_id) throws RegionException {
		try {
			Optional<RegionMaster> optional=regiondao.findById(region_id);
			if(optional.isPresent()) {				
				return optional.get();
			}else {
				throw new Exception("Invalid Region_id");
			}
		}catch(DataAccessException e) {
			throw new RegionException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegionException(e.getMessage(),e);
		}
	}

	@Override
	public List<RegionMaster> getAllRegion() throws RegionException {
		try {
			List<RegionMaster> regionList= regiondao.findAll();
			if(regionList.size()!= 0) {
				return regionList;
			}else {
				throw new Exception("No region in the database");
			}
		}catch(DataAccessException e) {
			throw new RegionException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegionException(e.getMessage(),e);
		}
	}

	@Override
	public Long deleteRegion(Long region_id) throws RegionException {
		try {
			regiondao.deleteById(region_id);
			return region_id;
		}catch(DataAccessException e) {
			throw new RegionException(e.getMessage(),e);
		}catch(Exception e) {
			throw new RegionException(e.getMessage(),e);
		}
	}
	

}
