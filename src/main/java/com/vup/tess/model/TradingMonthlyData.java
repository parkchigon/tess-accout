package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
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
@Table(name= "TBL_TRADING_MONTHLY_DATA")
public class TradingMonthlyData {
	
	/*
	 * @EmbeddedId private TradingMonthlyDataId seq;
	 */
	@Id
	@Column(nullable = false, length = 4)
	private String tradeYear;
	
	@Column(nullable = false, length = 2)
	private String tradeMonth;
	
	@Column(nullable = false, length = 6)
	private String factoryGroupId; 
	
	@Column(nullable = false, length = 6)
	private String energyGroupId;
	
	@Column(nullable = false, length = 6) 
	private String sourceFactoryId;
	
	@Column(nullable = false, length = 6) 
	private String sinkFactoryId;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingRoomBuySeq", referencedColumnName = "seq", insertable=false, updatable=false, nullable=false) 
	private TradingRoomBuy tradingRoomBuySeq;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingRoomSeq", referencedColumnName = "seq", insertable=false, updatable=false, nullable=false) 
	private TradingRoom tradingRoomSeq;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal tradeVolume;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal unitPrice;
	
	@Column(nullable = false)
	private char cancelFlag;
	
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
