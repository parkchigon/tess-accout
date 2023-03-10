package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.RawData;

@Repository
public interface RawDataRepository extends JpaRepository<RawData,String>{

	//List<RawData> findByPointMasterAndPointStatusDate(PointMaster tempPointmaster, String startDate);

	List<RawData> findByPointMasterAndPointStatusDate(PointMaster tempPointmaster, String startDate, Sort by);

}
