package com.delazari.traderisk.domain.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.delazari.traderisk.domain.Currency;

@Repository
public interface CurrencyRepository extends MongoRepository<Currency, String>{

	Currency findOneById(String id);
	Currency findOneByPairIgnoreCase(String pair);
	
	@Query("{$or: [ {id: [$eq : ?0}, {pair: [$eq : ?1]}]}")
	Currency searchOneByIdOrPair(String id, String pair);
	
}
