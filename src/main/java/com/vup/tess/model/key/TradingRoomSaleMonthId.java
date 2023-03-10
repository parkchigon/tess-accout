package com.vup.tess.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.vup.tess.model.TradingRoomSale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TradingRoomSaleMonthId implements Serializable{
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingRoomSaleSeq", referencedColumnName = "seq", insertable=false, updatable=false, nullable=false) 
	private TradingRoomSale tradingRoomSaleSeq;
	
	@Column(nullable = false, length = 4)
	private String saleYear;
	
	@Column(nullable = false, length = 2)
	private String saleMonth;
}
