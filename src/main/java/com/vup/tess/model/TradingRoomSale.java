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
@Table(name= "TBL_TRADING_ROOM_SALE")
public class TradingRoomSale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int seq;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingRoomSeq", referencedColumnName = "seq", insertable=false, updatable=false, nullable=false) 
	private TradingRoom tradingRoomSeq;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY
	@JoinColumn(name = "sourceFactoryId", referencedColumnName = "factoryId", unique=false, insertable=true, updatable=true, nullable=false) 
	private FactoryMaster sourceFactoryId;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal saleUnitPrice;
	
	@Column(nullable = false, length = 6)
	private String saleStartMonth;
	
	@Column(nullable = false, length = 6)
	private String saleEndMonth;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal monthSaleVolume;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal monthProductVolume;
	
	@Column(nullable = false)
	private char finishFlag;
	
	@CreationTimestamp
	@Column(nullable = false) 
	private LocalDateTime finishDate;
	
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
