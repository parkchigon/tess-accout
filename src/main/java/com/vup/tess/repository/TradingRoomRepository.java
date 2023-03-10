package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.EnergyGroup;
import com.vup.tess.model.FactoryGroup;
import com.vup.tess.model.PointMaster;
import com.vup.tess.model.TradingRoom;

@Repository
public interface TradingRoomRepository extends JpaRepository<TradingRoom,String>{

	//TradingRoom findByPointMaster(PointMaster tempPointmaster);

	TradingRoom findByFactoryGroupIdAndEnergyGroupId(FactoryGroup factoryGroup, EnergyGroup energyGroup);

	//TradingRoom findByFactoryGroupIdAndEnergyGroupId(String factoryGroupId, String energyGroupId);

}
