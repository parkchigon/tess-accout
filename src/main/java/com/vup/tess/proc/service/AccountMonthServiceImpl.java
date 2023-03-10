package com.vup.tess.proc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.RawData;
import com.vup.tess.model.SettlementBuy;
import com.vup.tess.model.SettlementBuyDaily;
import com.vup.tess.model.SettlementBuyFactory;
import com.vup.tess.model.SettlementBuyFactorys;
import com.vup.tess.model.SettlementSale;
import com.vup.tess.model.SettlementSaleDaily;
import com.vup.tess.repository.RawDataRepository;
import com.vup.tess.repository.SettlementBuyDailyRepository;
import com.vup.tess.repository.SettlementBuyFactoryRepository;
import com.vup.tess.repository.SettlementBuyFactorysRepository;
import com.vup.tess.repository.SettlementBuyRepository;
import com.vup.tess.repository.SettlementSaleDailyRepository;
import com.vup.tess.repository.SettlementSaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccountMonthServiceImpl implements AccountMonthService{
	@Autowired
	private SettlementBuyRepository settlementBuyRepository;
	
	@Autowired
	private SettlementBuyFactoryRepository settlementBuyFactoryRepository;
	
	@Autowired
	private RawDataRepository rawDataRepository;
	
	@Autowired
	private SettlementBuyDailyRepository settlementBuyDailyRepository;
	
	@Autowired
	private SettlementBuyFactorysRepository settlementBuyFactorysRepository;
	
	@Autowired
	private SettlementSaleRepository settlementSaleRepository;
	
	@Autowired
	private SettlementSaleDailyRepository settlementSaleDailyRepository;
	
	@Transactional(readOnly = false)
	public void settlementBuySave(SettlementBuy settlementBuy) {
		settlementBuyRepository.save(settlementBuy);
	}

	@Transactional(readOnly = false)
	public void settlementBuyFactorySave(SettlementBuyFactory settlementBuyFactory) {
		settlementBuyFactoryRepository.save(settlementBuyFactory);
	}

	@Transactional(readOnly = true)
	public List<RawData> findByPointMasterAndPointStatusDate(PointMaster tempPointmaster, String startDate, Sort by) {
		return rawDataRepository.findByPointMasterAndPointStatusDate(tempPointmaster, startDate, by);
	}

	@Transactional(readOnly = false)
	public void settlementBuyDailySave(SettlementBuyDaily settlementBuyDaily) {
		settlementBuyDailyRepository.save(settlementBuyDaily);
	}

	@Transactional(readOnly = true)
	public SettlementBuyFactorys findByTradingRoomSeqAndYearAndMonthAndSinkFactoryIdAndSourceFactoryId(
			int tradingRoomSeq, String tradeYear, String tradeMonth, String sinkFactoryId, String sourceFactoryId) {
		return settlementBuyFactorysRepository.findByTradingRoomSeqAndYearAndMonthAndSinkFactoryIdAndSourceFactoryId(tradingRoomSeq, tradeYear, tradeMonth, sinkFactoryId, sourceFactoryId);
	}

	@Transactional(readOnly = false)
	public void settlementSaleSave(SettlementSale settlementSale) {
		settlementSaleRepository.save(settlementSale);
	}

	@Transactional(readOnly = false)
	public void settlementSaleDailySave(SettlementSaleDaily settlementSaleDaily) {
		settlementSaleDailyRepository.save(settlementSaleDaily);
	}
	
}
