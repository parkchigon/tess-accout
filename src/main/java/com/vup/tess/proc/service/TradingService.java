package com.vup.tess.proc.service;

import java.util.List;

import com.vup.tess.model.Trading;
import com.vup.tess.model.TradingSale;

public interface TradingService {

	List<Trading> findBySinkFactoryIdAndTradeYearAndTradeMonth(String factoryId, String tradeYear, String tradeMonth);

	List<TradingSale> findBySourceFactoryId(String factoryId);

}
