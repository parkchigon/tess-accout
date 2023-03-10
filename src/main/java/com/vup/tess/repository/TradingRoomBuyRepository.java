package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.FactoryMaster;
import com.vup.tess.model.PointMaster;
import com.vup.tess.model.TradingRoomBuy;
import com.vup.tess.model.TradingRoomSale;

@Repository
public interface TradingRoomBuyRepository extends JpaRepository<TradingRoomBuy, Integer>{

	//List<TradingRoomBuy> findByTradingRoomSaleSeq(TradingRoomSale tradingRoomSaleSeq);

	List<TradingRoomBuy> findByTradingRoomSaleSeqAndSinkFactoryId(TradingRoomSale tradingRoomSaleSeq, String factoryId);

}
