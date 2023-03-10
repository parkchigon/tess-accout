package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import com.vup.tess.model.SectionChargeInfo;

@Repository
public interface SectionChargeRepository extends JpaRepository<SectionChargeInfo,String>{
	
	
	  @Query(
	  value="select FACTORY_GROUP_ID,ENERGY_GROUP_ID, ENERGY_DIRECT_CD, VUP_UNIT_PRICE, BASIC_CHARGE, VAT_RATE, VUP_FEE_RATE, max(apply_date) as apply_date, RESERVED1, RESERVED2, RESERVED3, SAVE_DATE, SAVE_ID"
	  + " from tbl_section_charge_info" + " where FACTORY_GROUP_ID = ?1" +
	  " and ENERGY_GROUP_ID = ?2" +
	  " and DATE_FORMAT(apply_date, 'yyyyMMdd') >= ?3" +
	  " and ENERGY_DIRECT_CD = ?4"+
	  " GROUP BY FACTORY_GROUP_ID,ENERGY_GROUP_ID", nativeQuery=true)
	  SectionChargeInfo selectCharge(String factoryGroupId, String energyGroupId,String now,String codeId);

	 
	 
}
