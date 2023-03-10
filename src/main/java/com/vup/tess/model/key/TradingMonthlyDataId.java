package com.vup.tess.model.key;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.vup.tess.model.EnergyGroup;
import com.vup.tess.model.FactoryGroup;
import com.vup.tess.model.FactoryMaster;
import com.vup.tess.model.TradingRoomBuy;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class TradingMonthlyDataId implements Serializable{
	
	@Column(nullable = false, length = 4)
	private String tradeYear;
	
	@Column(nullable = false, length = 2)
	private String tradeMonth;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY
	@JoinColumn(name = "factoryGroupId", referencedColumnName = "factoryGroupId", unique=false, insertable=true, updatable=true, nullable=false) 
	private FactoryGroup factoryGroupId; 
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "energyGroupId", referencedColumnName = "energyGroupId", insertable=false, updatable=false, nullable=false) 
	private EnergyGroup energyGroupId;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY
	@JoinColumn(name = "sourceFactoryId", referencedColumnName = "factoryId", unique=false, insertable=true, updatable=true, nullable=false) 
	private FactoryMaster sourceFactoryId;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY
	@JoinColumn(name = "sinkFactoryId", referencedColumnName = "factoryId", unique=false, insertable=true, updatable=true, nullable=false) 
	private FactoryMaster sinkFactoryId;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingRoomBuySeq", referencedColumnName = "seq", insertable=false, updatable=false, nullable=false) 
	private TradingRoomBuy tradingRoomBuySeq;
}
