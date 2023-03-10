package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
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
public class SettlementBuyFactory {
	
	@EmbeddedId
	private SettlementBuyFactoryId seq;
	
	@Column(nullable = false, precision = 5, scale = 1)
	private BigDecimal buyUnitVolume;
	
	@Column(nullable = false, precision = 15, scale = 3)
	private BigDecimal calcUsageVolume;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal calcPriceAmount;
	
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime saveDate;
	
	@Column(nullable = false, length = 20)
	private String saveId;
}
