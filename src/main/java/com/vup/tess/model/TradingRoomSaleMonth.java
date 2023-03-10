package com.vup.tess.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.vup.tess.model.key.TradingRoomSaleMonthId;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// @Getter, @Setter, @ToString @ RequiredArgsConstructor
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
@Table(name= "TBL_TRADING_ROOM_SALE_MONTH")
public class TradingRoomSaleMonth {
	@EmbeddedId
	private TradingRoomSaleMonthId seq;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal saleVolume;
}
