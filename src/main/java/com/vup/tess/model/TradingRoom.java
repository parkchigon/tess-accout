package com.vup.tess.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
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
@Table(name= "TBL_TRADING_ROOM")
public class TradingRoom {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int seq;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY
	@JoinColumn(name = "factoryGroupId", referencedColumnName = "factoryGroupId", unique=false, insertable=true, updatable=true, nullable=false) 
	private FactoryGroup factoryGroupId; 
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "energyGroupId", referencedColumnName = "energyGroupId", insertable=false, updatable=false, nullable=false) 
	private EnergyGroup energyGroupId;
	
	/*
	 * @ManyToOne(fetch = FetchType.EAGER) // EAGER, LAZY
	 * 
	 * @JoinColumns({@JoinColumn(name="factoryId", referencedColumnName="factoryId",
	 * insertable=false, updatable=false, nullable=false),
	 * 
	 * @JoinColumn(name="energyGroupId", referencedColumnName="energyGroupId",
	 * insertable=false, updatable=false, nullable=false),
	 * 
	 * @JoinColumn(name="pointId", referencedColumnName="pointId", insertable=false,
	 * updatable=false, nullable=false)}) private PointMaster pointMaster;
	 */
	@Column(nullable = true, length = 50)
	private String roomName;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "tradingModelCd", referencedColumnName = "codeId", insertable=false, updatable=false, nullable=false) 
	private CommonCode tradingModelCd;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "unitPriceTypeCd", referencedColumnName = "codeId", insertable=false, updatable=false, nullable=false) 
	private CommonCode unitPriceTypeCd;
	
	@Column(nullable = true, length = 6)
	private String roomStartMonth;
	
	@Column(nullable = true, length = 6)
	private String roomEndMonth;
	
	@Column(nullable = true, length = 4)
	private String roomStartHour;
	
	@Column(nullable = true, length = 4)
	private String roomEndHour;
	
	@Column(nullable = false, length = 1)
	private String limmitFactoryFlag;
	
	@Column(nullable = false, length = 1)
	private String displayFlag;
	
	@Column(nullable = false, length = 1)
	private String useFlag;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime updateDate;
	
	@Column(nullable = false, length = 20)
	private String updateId;
	
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private LocalDateTime saveDate;
	
	@Column(nullable = false, length = 20)
	private String saveId;
}
