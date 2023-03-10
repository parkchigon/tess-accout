package com.vup.tess.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

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
public class TradingSale {
	@Id
	private int seq;
	
	@Column(nullable = true)
	private int tradingRoomSeq;
	
	@Column(nullable = true)
	private String sourceFactoryId;
	
	@Column(nullable = true)
	private String sinkFactoryId;
	
	@Column(nullable = true)
	private BigDecimal saleUnitPrice;
	
	@Column(nullable = true)
	private BigDecimal monthSaleVolume;
	
	@Column(nullable = true)
	private String unitPriceTypeCd;
}
