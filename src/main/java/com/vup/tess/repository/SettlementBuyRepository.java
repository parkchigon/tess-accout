package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.SettlementBuy;
import com.vup.tess.model.key.SettlementBuyId;

@Repository
public interface SettlementBuyRepository extends JpaRepository<SettlementBuy,SettlementBuyId>{

}
