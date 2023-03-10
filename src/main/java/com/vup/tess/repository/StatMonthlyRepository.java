package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.StatMonthly;

@Repository
public interface StatMonthlyRepository extends JpaRepository<StatMonthly, String>{

	StatMonthly findByPointMasterAndStatDateMStartingWith(PointMaster tempPointmaster, String statDateM);

}
