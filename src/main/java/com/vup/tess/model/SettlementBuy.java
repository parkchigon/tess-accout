package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.vup.tess.model.key.SettlementBuyId;

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
@Table(name= "TBL_SETTLEMENT_BUY")
public class SettlementBuy {
	@EmbeddedId
	private SettlementBuyId seq;
	
	@Column(nullable = false, length = 10)
	private String pointId;
	
	@Column(nullable = false, length = 4)
	private String settlementStatusCd;
	
	@Column(nullable = false, length = 4)
	private String depositCd;
	
	@Column(nullable = false)
	private int alarmCount;
	
	@Column(nullable = false, precision = 12, scale = 3)
	private BigDecimal settlementAmount;
	
	@Column(nullable = false, precision = 12, scale = 3)
	private BigDecimal oriSettlementAmount;
	
	@Column(nullable = false, precision = 6, scale = 2)
	private BigDecimal totalBuyUnitVolume;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal totalUsingVolume;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal oriTotalUsingVolume;
	
	@Column(nullable = false, precision = 12, scale = 3)
	private BigDecimal usingPriceCharge;
	
	@Column(nullable = false, precision = 12, scale = 3)
	private BigDecimal oriUsingPriceCharge;
	
	@Column(nullable = false, precision = 12, scale = 3)
	private BigDecimal unitPrice;
	
	@Column(nullable = false, precision = 12, scale = 3)
	private BigDecimal basicUnitPrice;

	@Column(nullable = false, precision = 12, scale = 3)
	private BigDecimal interUnitPrice;
	
	@Column(nullable = true, columnDefinition = "TEXT")
	private String unitPricedesc;
	
	@Column(nullable = true, precision = 12, scale = 3)
	private BigDecimal basicPriceCharge;
	
	@Column(nullable = true, precision = 12, scale = 3)
	private BigDecimal excessCharge;
	
	@Column(nullable = true, precision = 12, scale = 3)
	private BigDecimal deductionCharge;
	
	@Column(nullable = true, precision = 12, scale = 3)
	private BigDecimal emissionCharge;
	
	@Column(nullable = true, precision = 12, scale = 3)
	private BigDecimal vatChatge;
	
	@Column(nullable = true, precision = 12, scale = 3)
	private BigDecimal vupFeeCharge;
	
	@Column(nullable = true, length = 20)
	private String billNo;
	
	@Column(nullable = true, length = 200)
	private String reviseReason;
	
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
