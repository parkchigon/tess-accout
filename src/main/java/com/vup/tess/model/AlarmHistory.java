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
@Table(name= "TBL_ALARM_HISTORY")
public class AlarmHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
	private int Seq;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "alarmId", referencedColumnName = "alarmId", insertable=false, updatable=false, nullable=false) 
	private AlarmMaster alarmId;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal warningMinValue;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal warningMaxValue;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal criticalMinValue;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal criticalMaxValue;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "alarmLevelCd", referencedColumnName = "codeId", insertable=false, updatable=false, nullable=false) 
	private CommonCode alarmLevelCd;
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "alarmConditionCd", referencedColumnName = "codeId", insertable=false, updatable=false, nullable=false) 
	private CommonCode alarmConditionCd;
	
	@Column(nullable = true, precision = 15, scale = 3)
	private BigDecimal alarmValue;
	
	@CreationTimestamp
	@Column(nullable = true) 
	private LocalDateTime alarmDate; 
	
	@ManyToOne(fetch = FetchType.EAGER)		// EAGER, LAZY 
	@JoinColumn(name = "alarmStatusCd", referencedColumnName = "codeId", insertable=false, updatable=false, nullable=false) 
	private CommonCode alarmStatusCd;
	
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
