package com.sapient.trg.service;

import java.util.List;

import com.sapient.trg.entity.TaxationMaster;


import com.sapient.trg.exception.TaxationException;

public interface TaxationService {
	public abstract Long addNewTaxation(TaxationMaster Taxation) throws TaxationException;
	public abstract TaxationMaster getTaxation(Long taxID) throws TaxationException;
	public abstract List<TaxationMaster> getAllTaxation() throws TaxationException;
	public abstract Long deleteTaxation(Long taxID) throws TaxationException;
	

}
