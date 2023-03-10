package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.TradingMonthlyData;
import com.vup.tess.model.TradingRoom;

@Repository
public interface TradingMonthlyDataRepository extends JpaRepository<TradingMonthlyData,String>{

	List<TradingMonthlyData> findByTradeYearAndTradeMonthAndTradingRoomSeq(String tradeYear, String tradeMonth,
			TradingRoom tradingroom);
	
}
