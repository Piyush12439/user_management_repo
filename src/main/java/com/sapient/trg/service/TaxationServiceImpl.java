package com.sapient.trg.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sapient.trg.dao.TaxationRepository;
import com.sapient.trg.entity.TaxationMaster;
import com.sapient.trg.exception.TaxationException;

@Service
@Transactional

public class TaxationServiceImpl implements TaxationService {
	
	@Autowired
	private TaxationRepository taxationdao;

	@Override
	public Long addNewTaxation(TaxationMaster Taxation) throws TaxationException {
		try {
			TaxationMaster savedTaxation = taxationdao.save(Taxation);
			return savedTaxation.getTaxId();
		}catch(DataAccessException e) {
			throw new TaxationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new TaxationException(e.getMessage(),e);
		}
	}

	@Override
	public TaxationMaster getTaxation(Long taxation_id) throws TaxationException {
		try {
			Optional<TaxationMaster> optional=taxationdao.findById(taxation_id);
			if(optional.isPresent()) {				
				return optional.get();
			}else {
				throw new Exception("Invalid Taxation_id");
			}
		}catch(DataAccessException e) {
			throw new TaxationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new TaxationException(e.getMessage(),e);
		}
	}

	@Override
	public List<TaxationMaster> getAllTaxation() throws TaxationException {
		try {
			List<TaxationMaster> taxationList= taxationdao.findAll();
			if(taxationList.size()!= 0) {
				return taxationList;
			}else {
				throw new Exception("No taxation in the database");
			}
		}catch(DataAccessException e) {
			throw new TaxationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new TaxationException(e.getMessage(),e);
		}
	}

	@Override
	public Long deleteTaxation(Long taxation_id) throws TaxationException {
		try {
			taxationdao.deleteById(taxation_id);
			return taxation_id;
		}catch(DataAccessException e) {
			throw new TaxationException(e.getMessage(),e);
		}catch(Exception e) {
			throw new TaxationException(e.getMessage(),e);
		}
	}
	

}
