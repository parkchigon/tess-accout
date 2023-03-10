package com.vup.tess.proc.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.RawData;
import com.vup.tess.model.SettlementBuy;
import com.vup.tess.model.SettlementBuyDaily;
import com.vup.tess.model.SettlementBuyFactory;
import com.vup.tess.model.SettlementBuyFactorys;
import com.vup.tess.model.SettlementSale;
import com.vup.tess.model.SettlementSaleDaily;

public interface AccountMonthService {

	public void settlementBuySave(SettlementBuy settlementBuy);

	public void settlementBuyFactorySave(SettlementBuyFactory settlementBuyFactory);

	public List<RawData> findByPointMasterAndPointStatusDate(PointMaster tempPointmaster, String startDate, Sort by);

	public void settlementBuyDailySave(SettlementBuyDaily settlementBuyDaily);

	public SettlementBuyFactorys findByTradingRoomSeqAndYearAndMonthAndSinkFactoryIdAndSourceFactoryId(
			int tradingRoomSeq, String tradeYear, String tradeMonth, String sinkFactoryId, String sourceFactoryId);

	public void settlementSaleSave(SettlementSale settlementSale);

	public void settlementSaleDailySave(SettlementSaleDaily settlementSaleDaily);

}
