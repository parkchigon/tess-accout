package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

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
//@Table(name= "TBL_TRADING_ROOM_BUY")
public class Trading {
	@Id
	private int seq;
	@Column(nullable = true)
	private int tradingRoomSeq;
	@Column(nullable = true, length = 4)
	private String unitPriceTypeCd;
	@Column(nullable = true, length = 4)
	private String tradeYear;
	@Column(nullable = true, length = 2)
	private String tradeMonth;
	@Column(nullable = true, length = 6)
	private String sourceFactoryId;
	@Column(nullable = true, length = 6)
	private String sinkFactoryId;
	@Column(nullable = true)
	private BigDecimal buyUnitVolume;
	@Column(nullable = true)
	private BigDecimal saleUnitPrice;
	
}
