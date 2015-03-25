package com.casestudy.tg;


import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface PriceEntryRepository extends MongoRepository<PriceEntry, String>{

	// return all documents in this repository
	public List<PriceEntry> findAll();
	
	// return the CurrentPrice object in the unique document
	// that matches the given value for field pid
	// NOTE: it seems that this query implies that CurrentPrice
	// must have a "pid" field ... I include a more general one
	// for the repository below
	// public CurrentPrice findByPid(long pid);
	
	// returns the PriceEntry that matches the pid
	public PriceEntry findByPid(long pid);
}
