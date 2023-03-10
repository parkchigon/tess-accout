package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vup.tess.model.SettlementBuyFactorys;

public interface SettlementBuyFactorysRepository extends JpaRepository<SettlementBuyFactorys,String>{

	SettlementBuyFactorys findByTradingRoomSeqAndYearAndMonthAndSinkFactoryIdAndSourceFactoryId(int tradingRoomSeq,
			String tradeYear, String tradeMonth, String sinkFactoryId, String sourceFactoryId);

}
