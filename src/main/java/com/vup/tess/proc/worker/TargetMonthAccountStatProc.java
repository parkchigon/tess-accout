package com.vup.tess.proc.worker;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vup.tess.global.util.DateUtils;
import com.vup.tess.global.util.KeyGenerator;
import com.vup.tess.model.EnergyGroup;
import com.vup.tess.model.FactoryMaster;
import com.vup.tess.model.PointMaster;
import com.vup.tess.model.RawData;
import com.vup.tess.model.SectionChargeInfo;
import com.vup.tess.model.SettlementBuy;
import com.vup.tess.model.SettlementBuyFactory;
import com.vup.tess.model.SettlementBuyFactorys;
import com.vup.tess.model.SettlementSale;
import com.vup.tess.model.SettlementSaleDaily;
import com.vup.tess.model.StatHourly;
import com.vup.tess.model.StatMonthly;
import com.vup.tess.model.Trading;
import com.vup.tess.model.TradingRoom;
import com.vup.tess.model.TradingSale;
import com.vup.tess.model.key.PointMasterId;
import com.vup.tess.model.key.SettlementBuyFactoryId;
import com.vup.tess.model.key.SettlementBuyId;
import com.vup.tess.model.key.SettlementSaleDailyId;
import com.vup.tess.model.key.SettlementSaleId;
import com.vup.tess.proc.service.AccountMonthService;
import com.vup.tess.proc.service.AlarmHistoryService;
import com.vup.tess.proc.service.EnergyGroupMasterService;
import com.vup.tess.proc.service.FactoryMasterService;
import com.vup.tess.proc.service.PointMasterService;
import com.vup.tess.proc.service.SectionChargeService;
import com.vup.tess.proc.service.StatHourlyService;
import com.vup.tess.proc.service.StatMonthlyService;
import com.vup.tess.proc.service.TradingService;

@Service
public class TargetMonthAccountStatProc {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired 
	private AccountMonthService accountMonthService;
	
	@Autowired 
	private PointMasterService pointMasterService;
	
	@Autowired 
	private FactoryMasterService factoryMasterService;
	
	@Autowired 
	private EnergyGroupMasterService energyGroupMasterService;
	
	@Autowired 
	private SectionChargeService sectionChargeService;
	
	@Autowired 
	private TradingService tradingService;
	
	@Autowired 
	private StatMonthlyService statMonthlyService;
	
	@Autowired 
	private StatHourlyService statHourlyService;
	
	@Autowired 
	private AlarmHistoryService alarmHistoryService;
	
	
	public void doService() {
		try {
			logger.info("########### Start target amount management statistics ########### ");
			
			List<PointMaster> pointmasterList = pointMasterService.findByPointChargeFlag(Sort.by(Sort.Direction.DESC,"energyDirectCd"), "Y"); 
			logger.info("pointmasterList::" + pointmasterList.toString());
			logger.info("pointmasterList.size::" + pointmasterList.size());
			if (pointmasterList.size() != 0) {
				for (int i = 0; i < pointmasterList.size(); i++) {
					if (pointmasterList.get(i).getEnergyDirectCd().getCodeId().equals("4102")) {
						//sinkFactoryID 조회
						Optional<FactoryMaster> factoryId = factoryMasterService
								.findById(pointmasterList.get(i).getSeq().getFactoryId().getFactoryId());
						Optional<EnergyGroup> energyGroupIds = energyGroupMasterService.findById(
								pointmasterList.get(i).getSeq().getEnergyGroupId().getEnergyGroupId());
						String factoryGroupId = pointmasterList.get(i).getSeq().getFactoryId().getFactoryGroupId()
								.getFactoryGroupId();
						String energyGroupId = pointmasterList.get(i).getSeq().getEnergyGroupId().getEnergyGroupId();
						logger.info("factoryGroupId::" + factoryGroupId);
						logger.info("energyGroupId::" + energyGroupId);
						// 현재날짜가져오기
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
						Calendar time = Calendar.getInstance();
						String now = format.format(time.getTime());
						// 구간요금제 정보 조회
						SectionChargeInfo sectionChargeInfo = sectionChargeService.selectCharge(factoryGroupId,energyGroupId, now, pointmasterList.get(i).getEnergyDirectCd().getCodeId());
						logger.info("sectionChargeInfo::" + sectionChargeInfo.toString());
						
						time.add(Calendar.MONTH, -1);
						String beforeMonth = format.format(time.getTime());
						String tradeYear = beforeMonth.substring(0, 4);
						String tradeMonth = beforeMonth.substring(4, 6);
						logger.info("tradeYear::" + tradeYear);
						logger.info("tradeMonth::" + tradeMonth);
						
						List<Trading> trading = tradingService.findBySinkFactoryIdAndTradeYearAndTradeMonth(factoryId.get().getFactoryId(),tradeYear,tradeMonth);
						logger.info("trading::"+trading);
						if(trading.size() == 0) {
							logger.info("no trading data !!!");
							continue;
						}else {
							String pointId = pointmasterList.get(i).getSeq().getPointId();
							//수요자 월 정산정보 초기값 저장	
							TradingRoom tradingroom = new TradingRoom();
							tradingroom.setSeq(trading.get(0).getTradingRoomSeq());
							
							SettlementBuy settlementBuy = null;
							SettlementBuyId settlementBuyId = new SettlementBuyId();
							settlementBuyId.setYear(tradeYear);
							settlementBuyId.setMonth(tradeMonth);
							settlementBuyId.setTradingRoomSeq(tradingroom);
							settlementBuyId.setSinkFactoryId(factoryId.get());

							// 초기값 not null 값들 0으로 설정
							BigDecimal zero = BigDecimal.ZERO;

							String settlementStatusCd = "4402";
							String depoitCd = "4501";
							
							String billNo = null;
							try {
								billNo = KeyGenerator.createCommonShardKey(Integer.parseInt("01"));
							} catch (Exception e) {
								e.printStackTrace();
							}


							settlementBuy = SettlementBuy.builder().seq(settlementBuyId).totalBuyUnitVolume(zero)
									.totalUsingVolume(zero).oriTotalUsingVolume(zero).pointId(pointId)
									.usingPriceCharge(zero).oriUsingPriceCharge(zero).settlementAmount(zero)
									.alarmCount(0).unitPrice(zero)
									.oriSettlementAmount(zero).settlementStatusCd(settlementStatusCd)
									.depositCd(depoitCd).billNo(billNo).saveId("admin").updateId("admin").build();
							accountMonthService.settlementBuySave(settlementBuy);
							//고정단가
							//if(trading.get(0).getUnitPriceTypeCd().equals("4201")) {
								//총 계약량 
							List<BigDecimal> tempTotalValue = new LinkedList<>();
							for(int j = 0 ; j < trading.size(); j++) {
								tempTotalValue.add(trading.get(j).getBuyUnitVolume());
							}
							BigDecimal totalValue = BigDecimal.ZERO;
							for(BigDecimal value : tempTotalValue) {
								if(value == null) {
									value = BigDecimal.ZERO;
								}
								totalValue = totalValue.add(value);
						    }
							List<BigDecimal> tempTotalUsageVolum = new LinkedList<>();
							List<BigDecimal> tempTotalPriceAmount = new LinkedList<>();
							String statDateM = beforeMonth.substring(0, 6);
							logger.info("statDateM::" + statDateM);
							PointMasterId pointMasterId = new PointMasterId();
							pointMasterId.setFactoryId(factoryId.get());
							pointMasterId.setEnergyGroupId(energyGroupIds.get());
							pointMasterId.setPointId(pointId);

							PointMaster tempPointmaster = new PointMaster();

							tempPointmaster.setSeq(pointMasterId);
							for(int k = 0 ; k < trading.size(); k++){
								List<StatHourly> statHourly = statHourlyService
										.findByPointMasterAndStatDateHStartingWith(tempPointmaster, statDateM);
								if (statHourly.size() == 0) {
									logger.info("no Stat Monthly Data!!!");
									continue;
								} else {
									List<BigDecimal> tempExcessVolume = new LinkedList<>();
									List<BigDecimal> tempTotalVolume = new LinkedList<>();
									for (int h = 0 ; h < statHourly.size() ; h++) {
										if(statHourly.get(h).getPointValueFix() == null) {
											if(trading.get(k).getBuyUnitVolume().compareTo(statHourly.get(h).getPointValue()) < -1) {
												tempExcessVolume.add(trading.get(k).getBuyUnitVolume().subtract(statHourly.get(h).getPointValue()).abs());												
												tempTotalVolume.add(trading.get(k).getBuyUnitVolume());
											}
											tempTotalVolume.add(statHourly.get(h).getPointValue());
										}else {
											
										}
									}
									//수요 공급공장별 정산 테이블 저장
									//공급별 예측수요량 계산
									BigDecimal totalVolum = trading.get(k).getBuyUnitVolume().divide(totalValue).multiply(statHourly.getPointValue());
									//페널티 정책 추가 예정
									//공급단가 총액 계산
									BigDecimal hundred = new BigDecimal(100);
									BigDecimal vatRate =sectionChargeInfo.getVatRate().divide(hundred);
									BigDecimal vatFeeRate =sectionChargeInfo.getVupFeeRate().divide(hundred);
									logger.info("vatFeeRate::"+vatFeeRate);
									//BigDecimal priceAmount = sectionChargeInfo.getVupUnitPrice().add(sectionChargeInfo.getBasicCharge()).add(sectionChargeInfo.getVupFeeRate()).multiply(BigDecimal.ONE.subtract(vatRate)).setScale(3,BigDecimal.ROUND_HALF_UP);
									//고정단가
									BigDecimal price = BigDecimal.ZERO;
									if(trading.get(0).getUnitPriceTypeCd().equals("4201")) {
										price = totalVolum.multiply(vatRate);	
									}else {
										price = totalVolum.multiply(trading.get(k).getSaleUnitPrice());	
									}
									BigDecimal addPrice = price.multiply(vatFeeRate);
									logger.info("sectionChargeInfo.getBasicCharge()"+sectionChargeInfo.getBasicCharge());
									logger.info("price::"+price);
									logger.info("addPrice::"+addPrice);
									BigDecimal priceAmount = sectionChargeInfo.getBasicCharge().add(price).setScale(3, BigDecimal.ROUND_HALF_UP);
									logger.info("priceAmount::"+priceAmount);
									//sinkFactory
									FactoryMaster sinkFactory = new FactoryMaster();
									sinkFactory.setFactoryId(trading.get(k).getSinkFactoryId());
									//sourceFactory
									FactoryMaster sourceFactory = new FactoryMaster();
									sourceFactory.setFactoryId(trading.get(k).getSourceFactoryId());
									SettlementBuyFactory settlementBuyFactory = null;
									SettlementBuyFactoryId settlementBuyFactoryId = new SettlementBuyFactoryId();
									settlementBuyFactoryId.setTradingRoomSeq(tradingroom);
									settlementBuyFactoryId.setYear(tradeYear);;
									settlementBuyFactoryId.setMonth(tradeMonth);
									settlementBuyFactoryId.setSinkFactoryId(sinkFactory);
									settlementBuyFactoryId.setSourceFactoryId(sourceFactory);
									
									settlementBuyFactory = SettlementBuyFactory.builder().seq(settlementBuyFactoryId)
											.buyUnitVolume(trading.get(k).getBuyUnitVolume()).calcUsageVolume(totalVolum)
											.calcPriceAmount(sectionChargeInfo.getVupUnitPrice()).saveId("admin").build();
									accountMonthService.settlementBuyFactorySave(settlementBuyFactory);
									
									tempTotalUsageVolum.add(totalVolum);
									tempTotalPriceAmount.add(priceAmount);
								}
							}
							BigDecimal totalUsageVolum = BigDecimal.ZERO;
							for(BigDecimal value : tempTotalUsageVolum) {
								if(value == null) {
									value = BigDecimal.ZERO;
								}
								totalUsageVolum = totalUsageVolum.add(value);
						    }
							
							BigDecimal totalPriceAmount = BigDecimal.ZERO;
							for(BigDecimal value : tempTotalPriceAmount) {
								if(value == null) {
									value = BigDecimal.ZERO;
								}
								totalPriceAmount = totalPriceAmount.add(value);
						    }
							//알람카운트
							int alarm =(int) alarmHistoryService.countAlarm(factoryId.get().getFactoryId(),pointId,statDateM);
							
							settlementBuy = SettlementBuy.builder().seq(settlementBuyId).totalBuyUnitVolume(totalValue)
							.totalUsageVolume(totalUsageVolum).oriTotalUsageVolume(totalUsageVolum).pointId(pointId)
							.totalPriceAmount(totalPriceAmount).oriTotalPriceAmount(totalPriceAmount).settlementAmount(zero)
							.alarmCount(alarm).basicChargeAmount(sectionChargeInfo.getBasicCharge()).vatAmount(sectionChargeInfo.getVatRate()).feeAmount(sectionChargeInfo.getVupFeeRate())
							.oriSettlementAmount(zero).settlementStatusCd(settlementStatusCd).billNo(billNo)
							.depositCd(depoitCd).saveId("admin").updateId("admin").build();
							accountMonthService.settlementBuySave(settlementBuy);
							
							//수요자 일별 사용량 조회 및 디비 저장 --삭제
							/*
							 * int maxDate = DateUtils.getMonthMaxDay(beforeMonth);
							 * logger.info("maxDate::"+maxDate); for(int l = 1 ; l <= maxDate; l++) { String
							 * day = DateUtils.getDayToString(l); logger.info("day::"+day); String startDate
							 * = statDateM+day;
							 * if(pointmasterList.get(i).getPointValueTypeCd().getCodeId().equals("3102")) {
							 * logger.error("순시값입니다."); continue; }else { List<RawData> dayList
							 * =accountMonthService.findByPointMasterAndPointStatusDate(tempPointmaster,
							 * startDate, Sort.by(Sort.Direction.ASC, "saveDate"));
							 * logger.info("dayList::"+dayList.toString()); SettlementBuyDaily
							 * settlementBuyDaily = null; SettlementBuyDailyId settlementBuyDailyId = new
							 * SettlementBuyDailyId(); settlementBuyDailyId.setTradingRoomSeq(tradingroom);
							 * settlementBuyDailyId.setYear(tradeYear);
							 * settlementBuyDailyId.setMonth(tradeMonth);
							 * settlementBuyDailyId.setSinkFactoryId(factoryId.get());
							 * settlementBuyDailyId.setDay(day); if(dayList.size() == 0) { //unll 입력
							 * settlementBuyDaily = SettlementBuyDaily.builder() .seq(settlementBuyDailyId)
							 * .saveId("admin").build();
							 * accountMonthService.settlementBuyDailySave(settlementBuyDaily); }else {
							 * //리스트에서 처음과 마지막 값을 가져와 마지막 값 - 처음 값 BigDecimal firstValue =
							 * dayList.get(0).getPointValue(); BigDecimal lastvalue =
							 * dayList.get(dayList.size() -1).getPointValue();
							 * 
							 * BigDecimal diffValue = lastvalue.subtract(firstValue);
							 * 
							 * //저장 settlementBuyDaily = SettlementBuyDaily.builder()
							 * .seq(settlementBuyDailyId).dayVolume(diffValue).dayMinValue(firstValue)
							 * .dayMaxValue(lastvalue).saveId("system").build();
							 * accountMonthService.settlementBuyDailySave(settlementBuyDaily);
							 * 
							 * } } }
							 */
						}

					}else {
						String billNo = null;
						try {
							billNo = KeyGenerator.createCommonShardKey(Integer.parseInt("01"));
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						//sorceFactoryID 조회
						Optional<FactoryMaster> factoryId = factoryMasterService
								.findById(pointmasterList.get(i).getSeq().getFactoryId().getFactoryId());
						Optional<EnergyGroup> energyGroupIds = energyGroupMasterService.findById(
								pointmasterList.get(i).getSeq().getEnergyGroupId().getEnergyGroupId());
						String factoryGroupId = pointmasterList.get(i).getSeq().getFactoryId().getFactoryGroupId()
								.getFactoryGroupId();
						String energyGroupId = pointmasterList.get(i).getSeq().getEnergyGroupId().getEnergyGroupId();
						logger.info("factoryGroupId::" + factoryGroupId);
						logger.info("energyGroupId::" + energyGroupId);
						// 현재날짜가져오기
						SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
						Calendar time = Calendar.getInstance();
						String now = format.format(time.getTime());
						// 구간요금제 정보 조회
						SectionChargeInfo sectionChargeInfo = sectionChargeService.selectCharge(factoryGroupId,energyGroupId, now, pointmasterList.get(i).getEnergyDirectCd().getCodeId());
						logger.info("sectionChargeInfo::" + sectionChargeInfo.toString());
						
						time.add(Calendar.MONTH, -1);
						String beforeMonth = format.format(time.getTime());
						String tradeYear = beforeMonth.substring(0, 4);
						String tradeMonth = beforeMonth.substring(4, 6);
						logger.info("tradeYear::" + tradeYear);
						logger.info("tradeMonth::" + tradeMonth);
						String pointId = pointmasterList.get(i).getSeq().getPointId();
						String statDateM = tradeYear + tradeMonth;
						int alarm =(int) alarmHistoryService.countAlarm(factoryId.get().getFactoryId(),pointId,statDateM);
						List<TradingSale> tradingSale = tradingService.findBySourceFactoryId(factoryId.get().getFactoryId());
						logger.info("tradingRoomSale::"+tradingSale);
						if(tradingSale.size() == 0) {
						 logger.info("no trading sale data!!!!");
						 continue;
						}
						//총 사용량 및 금액 합
						List<BigDecimal> tempTotalTradeVolume = new LinkedList<>();
						List<BigDecimal> tempTotalPriceAmount = new LinkedList<>();
						for(int j =0 ; j < tradingSale.size(); j++) {
							SettlementBuyFactorys settlementBuyFactorys = accountMonthService.findByTradingRoomSeqAndYearAndMonthAndSinkFactoryIdAndSourceFactoryId(tradingSale.get(j).getTradingRoomSeq(),tradeYear,tradeMonth,tradingSale.get(j).getSinkFactoryId(),tradingSale.get(j).getSourceFactoryId());
							logger.info("settlementBuyFactorys::"+settlementBuyFactorys);
							if(settlementBuyFactorys == null) {
								continue;
							}
							tempTotalTradeVolume.add(settlementBuyFactorys.getBuyVolume());
							tempTotalPriceAmount.add(settlementBuyFactorys.getPriceAmount());
							
						}
						BigDecimal totalTradeVolume = BigDecimal.ZERO;
						for(BigDecimal value : tempTotalTradeVolume) {
							if(value == null) {
								value = BigDecimal.ZERO;
							}
							totalTradeVolume = totalTradeVolume.add(value);
					    }
						
						BigDecimal totalPriceAmount = BigDecimal.ZERO;
						for(BigDecimal value : tempTotalPriceAmount) {
							if(value == null) {
								value = BigDecimal.ZERO;
							}
							totalPriceAmount = totalPriceAmount.add(value);
					    }
						//사용량 계산 수식 추가
						//공급량 월 통계 조회
						PointMasterId pointMasterId = new PointMasterId();
						pointMasterId.setFactoryId(factoryId.get());
						pointMasterId.setEnergyGroupId(energyGroupIds.get());
						pointMasterId.setPointId(pointId);

						PointMaster tempPointmaster = new PointMaster();

						tempPointmaster.setSeq(pointMasterId);
						StatMonthly statMonthly = statMonthlyService.findByPointMasterAndStatDateMStartingWith(tempPointmaster, statDateM);
						if(statMonthly.getPointValue() == null){
							continue;
						}
						TradingRoom tradingRooms = new TradingRoom();
						tradingRooms.setSeq(tradingSale.get(0).getTradingRoomSeq());
						SettlementSaleId settlementSaleId = new SettlementSaleId();
						settlementSaleId.setTradingRoomSeq(tradingRooms);
						settlementSaleId.setYear(tradeYear);
						settlementSaleId.setMonth(tradeMonth);
						settlementSaleId.setSourceFactoryId(factoryId.get());
						BigDecimal basicCharge = BigDecimal.ZERO;
						if (tradingSale.get(0).getUnitPriceTypeCd().equals("4201")) {
							basicCharge = sectionChargeInfo.getBasicCharge();
						}else {
							basicCharge = tradingSale.get(i).getSaleUnitPrice();
						}
						logger.info("statMonthly.getPointValue()::"+statMonthly.getPointValue());
						SettlementSale settlementSale = null;
						settlementSale = SettlementSale.builder().seq(settlementSaleId).totalTradeVolume(totalTradeVolume).totalSupplyVolume(statMonthly.getPointValue())
								.oriTotalSupplyVolume(statMonthly.getPointValue()).pointId(pointId).totalPriceAmount(totalPriceAmount).oriTotalPriceAmount(totalPriceAmount)
								.basicChargeAmount(basicCharge).vatAmount(sectionChargeInfo.getVatRate()).feeAmount(sectionChargeInfo.getVupFeeRate())
								.billNo(billNo).settlementAmount(basicCharge).oriSettlementAmount(basicCharge).settlementStatusCd("4402").paymentCd("4501")
								.alarmCount(alarm).updateId("admin").saveId("admin").build();
						accountMonthService.settlementSaleSave(settlementSale);
						
						//공급자 일별 사용량 조회 및 디비 저장 
						int maxDate = DateUtils.getMonthMaxDay(beforeMonth);
						logger.info("maxDate::"+maxDate); 
						for(int l = 1 ; l <= maxDate; l++) { 
							String day = DateUtils.getDayToString(l); 
							logger.info("day::"+day); 
							String startDate = statDateM+day;
							if(pointmasterList.get(i).getPointValueTypeCd().getCodeId().equals("3102")) {
								logger.error("순시값입니다.");
								continue;
							}else { 
								List<RawData> dayList =accountMonthService.findByPointMasterAndPointStatusDate(tempPointmaster,startDate, Sort.by(Sort.Direction.ASC, "saveDate"));
								logger.info("dayList::"+dayList.toString()); 
								SettlementSaleDaily settlementSaleDaily = null; 
								SettlementSaleDailyId settlementSaleDailyId = new SettlementSaleDailyId(); 
								settlementSaleDailyId.setTradingRoomSeq(tradingRooms);
								settlementSaleDailyId.setYear(tradeYear);
								settlementSaleDailyId.setMonth(tradeMonth);
								settlementSaleDailyId.setSourceFactoryId(factoryId.get());
								settlementSaleDailyId.setDay(day); 
								if(dayList.size() == 0) {
									//unll 입력
									settlementSaleDaily = SettlementSaleDaily.builder().seq(settlementSaleDailyId)
									.saveId("admin").build();
									accountMonthService.settlementSaleDailySave(settlementSaleDaily); 
								 }else { 
									//리스트에서 처음과 마지막 값을 가져와 마지막 값 - 처음 값 
									BigDecimal firstValue = dayList.get(0).getPointValue(); 
									BigDecimal lastvalue = dayList.get(dayList.size() -1).getPointValue();
								  
									BigDecimal diffValue = lastvalue.subtract(firstValue);
								  
									//저장 
									settlementSaleDaily = SettlementSaleDaily.builder()
									.seq(settlementSaleDailyId).dayVolume(diffValue).dayMinValue(firstValue)
									.dayMaxValue(lastvalue).saveId("system").build();
									accountMonthService.settlementSaleDailySave(settlementSaleDaily);
								}
							}
						}
					}
				}
			} else {
				logger.debug("no Pointer Master data!!!");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
