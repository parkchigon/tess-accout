package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.SettlementBuyFactory;
import com.vup.tess.model.key.SettlementBuyFactoryId;

@Repository
public interface SettlementBuyFactoryRepository extends JpaRepository<SettlementBuyFactory,SettlementBuyFactoryId>{

	SettlementBuyFactory findBySeq(SettlementBuyFactory tempSettlementBuyFactory);

	//SettlementBuyFactory findById(SettlementBuyFactory tempSettlementBuyFactory);

}
