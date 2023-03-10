package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.StatHourly;

@Repository
public interface StatHourlyRepository  extends JpaRepository<StatHourly, String>{

	List<StatHourly> findByPointMasterAndStatDateHStartingWith(PointMaster tempPointmaster, String statDateM);

}
