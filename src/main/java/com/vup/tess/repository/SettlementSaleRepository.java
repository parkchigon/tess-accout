package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.SettlementSale;
import com.vup.tess.model.key.SettlementSaleId;

@Repository
public interface SettlementSaleRepository extends JpaRepository<SettlementSale, SettlementSaleId>{

}
