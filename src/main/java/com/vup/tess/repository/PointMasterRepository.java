package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.key.PointMasterId;

@Repository
public interface PointMasterRepository extends JpaRepository<PointMaster, PointMasterId>{

	List<PointMaster> findByPointChargeFlag(Sort by, String pointChargeFlag);

	

}
