package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.SettlementSaleDaily;
import com.vup.tess.model.key.SettlementSaleDailyId;

@Repository
public interface SettlementSaleDailyRepository extends JpaRepository<SettlementSaleDaily, SettlementSaleDailyId>{

}
