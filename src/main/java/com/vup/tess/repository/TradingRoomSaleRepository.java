package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.FactoryMaster;
import com.vup.tess.model.TradingRoom;
import com.vup.tess.model.TradingRoomSale;

@Repository
public interface TradingRoomSaleRepository extends JpaRepository<TradingRoomSale, Integer>{

	//List<TradingRoomSale> findByTradingRoomSeq(TradingRoom tradingroom);

	List<TradingRoomSale> findBySourceFactoryId(FactoryMaster factoryMaster);

}
