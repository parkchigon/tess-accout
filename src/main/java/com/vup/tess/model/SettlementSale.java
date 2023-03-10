package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.vup.tess.model.key.SettlementSaleId;

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
@Table(name= "TBL_SETTLEMENT_SALE")
public class SettlementSale {
	@EmbeddedId
	private SettlementSaleId seq;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal totalTradeVolume;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal totalSupplyVolume;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal oriTotalSupplyVolume;
	
	@Column(nullable = false, length = 10)
	private String pointId;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal totalPriceAmount;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal oriTotalPriceAmount;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal basicChargeAmount;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal vatAmount;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal feeAmount;
	
	@Column(nullable = false, length = 20)
	private String billNo;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal settlementAmount;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal oriSettlementAmount;
	
	@Column(nullable = true, length = 200)
	private String reviseReason;
	
	@Column(nullable = false, length = 4)
	private String settlementStatusCd;
	
	@Column(nullable = false, length = 4)
	private String paymentCd;
	
	@Column(nullable = false)
	private int alarmCount;
	
	@CreationTimestamp
	@Column(nullable = false) 
	private LocalDateTime updateDate; 
	
	@Column(nullable = false, length = 20)
	private String updateId;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime saveDate;
	
	@Column(nullable = false, length = 20)
	private String saveId;
}
