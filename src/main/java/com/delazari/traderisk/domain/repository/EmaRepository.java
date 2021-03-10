package com.delazari.traderisk.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.delazari.traderisk.domain.Ema;

@Repository
public interface EmaRepository extends MongoRepository<Ema, String>{

	Ema findOneById(String id);
	
}
							