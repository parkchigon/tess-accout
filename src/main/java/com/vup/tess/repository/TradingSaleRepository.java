package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.FactoryMaster;
import com.vup.tess.model.TradingRoomSale;
import com.vup.tess.model.TradingSale;

@Repository
public interface TradingSaleRepository extends JpaRepository<TradingSale,String>{

	@Query(value="select ttrs.SEQ as SEQ, ttrs.TRADING_ROOM_SEQ as TRADING_ROOM_SEQ, ttrs.SOURCE_FACTORY_ID as SOURCE_FACTORY_ID, ttrs.MONTH_SALE_VOLUME as MONTH_SALE_VOLUME, ttrb.SINK_FACTORY_ID as SINK_FACTORY_ID, ttrs.SALE_UNIT_PRICE as SALE_UNIT_PRICE, ttr.UNIT_PRICE_TYPE_CD as UNIT_PRICE_TYPE_CD\r\n"
			+ "  from tbl_trading_room_sale ttrs left join tbl_trading_room_buy ttrb on ttrs.SEQ = ttrb.TRADING_ROOM_SALE_SEQ \r\n"
			+ "  left join tbl_trading_room ttr on ttrs.TRADING_ROOM_SEQ = ttr.SEQ"
			+ " where ttrs.SOURCE_FACTORY_ID = ?1", nativeQuery=true)
	List<TradingSale> findBySourceFactoryId(String factoryId);

}
