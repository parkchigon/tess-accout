package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name= "TBL_TRADING_ROOM_BUY")
public class TradingRoomBuy {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int seq;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingRoomSaleSeq", referencedColumnName = "seq", insertable=false, updatable=false, nullable=false) 
	private TradingRoomSale tradingRoomSaleSeq;
	
	@Column(nullable = false, length = 6)
	private String sinkFactoryId;
	
	@Column(nullable = false, length = 6)
	private String buyStartMonth;
	
	@Column(nullable = false, length = 6)
	private String buyEndMonth;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal monthBuyVolume;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal monthMaxVolume;
	
	@CreationTimestamp
	@Column(nullable = false) 
	private LocalDateTime tradingDate;
	
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
