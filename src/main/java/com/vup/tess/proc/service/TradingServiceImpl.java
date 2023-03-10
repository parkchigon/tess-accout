package com.vup.tess.proc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.model.Trading;
import com.vup.tess.model.TradingSale;
import com.vup.tess.repository.TradingRepository;
import com.vup.tess.repository.TradingSaleRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TradingServiceImpl implements TradingService{
	
	@Autowired
	private TradingRepository tradingRepository;
	
	@Autowired
	private TradingSaleRepository tradingSaleRepository;
	
	@Transactional(readOnly = true)
	public List<Trading> findBySinkFactoryIdAndTradeYearAndTradeMonth(String factoryId, String tradeYear,String tradeMonth) {
		return tradingRepository.findBySinkFactoryIdAndTradeYearAndTradeMonth(factoryId, tradeYear, tradeMonth);
	}

	@Transactional(readOnly = true)
	public List<TradingSale> findBySourceFactoryId(String factoryId) {
		// TODO Auto-generated method stub
		return tradingSaleRepository.findBySourceFactoryId(factoryId);
	}

}
