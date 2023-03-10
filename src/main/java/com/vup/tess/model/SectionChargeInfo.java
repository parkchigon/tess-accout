package com.vup.tess.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import com.vup.tess.model.key.SectionChargeInfoId;

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
@Table(name= "TBL_SECTION_CHARGE_INFO")
public class SectionChargeInfo {

	@EmbeddedId
	private SectionChargeInfoId seq;	
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal vupUnitPrice;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal basicCharge;
	
	@Column(nullable = false, precision = 5, scale = 2)
	private BigDecimal vatRate;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal vupFeeRate;
	
	@Column(nullable = true, precision = 10, scale = 3)
	private BigDecimal reserved1;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal reserved2;
	
	@Column(nullable = false, precision = 10, scale = 3)
	private BigDecimal reserved3;
	
	@CreationTimestamp
	@Column(nullable = false)
	private LocalDateTime saveDate;
	
	@Column(nullable = false, length = 20)
	private String saveId;
}
