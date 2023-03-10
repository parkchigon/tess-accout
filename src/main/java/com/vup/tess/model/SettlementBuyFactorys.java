package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.vup.tess.model.key.SettlementBuyFactoryId;

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
@Table(name= "TBL_SETTLEMENT_BUY_FACTORY")
public class SettlementBuyFactorys {
	
	@Id
	private int tradingRoomSeq;
	
	@Column(nullable = false, length = 4)
	private String year;
	
	@Column(nullable = false, length = 2)
	private String month;
	
	@Column(nullable = false, length = 6)
	private String sinkFactoryId;
	
	@Column(nullable = false, length = 6)
	private String sourceFactoryId;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal buyVolume;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal calcVolume;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal unitPrice;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal penaltyVal;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal priceAmount;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime saveDate;
	
	@Column(nullable = false, length = 20)
	private String saveId;
}
