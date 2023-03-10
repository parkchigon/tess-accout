package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.vup.tess.model.key.SettlementBuyDailyId;

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
@Table(name= "TBL_SETTLEMENT_BUY_DAILY")
public class SettlementBuyDaily {
	@EmbeddedId
	private SettlementBuyDailyId seq;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal dayVolume;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal dayMinValue;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal dayMaxValue;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime saveDate;
	
	@Column(nullable = false, length = 20)
	private String saveId;
}
