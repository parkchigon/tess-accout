package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.SettlementBuyDaily;

@Repository
public interface SettlementBuyDailyRepository extends JpaRepository<SettlementBuyDaily,String>{

}
