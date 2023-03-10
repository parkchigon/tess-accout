package com.vup.tess.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.vup.tess.model.FactoryMaster;
import com.vup.tess.model.TradingRoom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class SettlementSaleDailyId implements Serializable{
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingRoomSeq", referencedColumnName = "seq", insertable=false, updatable=false, nullable=false) 
	private TradingRoom tradingRoomSeq;
	
	@Column(nullable = false, length = 4)
	private String year;
	
	@Column(nullable = false, length = 2)
	private String month;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "sourceFactoryId", referencedColumnName = "factoryId", insertable=false, updatable=false, nullable=false) 
	private FactoryMaster sourceFactoryId;
	
	@Column(nullable = false, length = 2)
	private String day;
}
