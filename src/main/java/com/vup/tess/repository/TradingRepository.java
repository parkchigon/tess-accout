package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.Trading;

@Repository
public interface TradingRepository extends JpaRepository<Trading,String>{

	@Query(value="select ttrb.SEQ as SEQ, ttmd.TRADING_ROOM_SEQ as TRADING_ROOM_SEQ, ttr.UNIT_PRICE_TYPE_CD as UNIT_PRICE_TYPE_CD, ttmd.TRADE_YEAR as trade_year, ttmd.TRADE_MONTH as TRADE_MONTH, ttmd.SOURCE_FACTORY_ID as SOURCE_FACTORY_ID, ttmd.SINK_FACTORY_ID as  SINK_FACTORY_ID, ttmd.TRADE_VOLUME as TRADE_VOLUME, ttmd.UNIT_PRICE as UNIT_PRICE \r\n"
			+ "from tbl_trading_room_buy ttrb left join tbl_trading_monthly_data ttmd on ttrb.SEQ = ttmd.TRADING_ROOM_BUY_SEQ\r\n"
			+ "left join tbl_trading_room ttr on ttmd.TRADING_ROOM_SEQ = ttr.SEQ\r\n"
			+ "where ttrb.SINK_FACTORY_ID = ?1 and ttmd.TRADE_YEAR=?2 and ttmd.trade_month = ?3", nativeQuery=true)
	List<Trading> findBySinkFactoryIdAndTradeYearAndTradeMonth(String factoryId, String tradeYear, String tradeMonth);

}
