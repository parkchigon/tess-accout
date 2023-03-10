package com.vup.tess.proc.service;

import java.util.List;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.StatHourly;

public interface StatHourlyService {

	List<StatHourly> findByPointMasterAndStatDateHStartingWith(PointMaster tempPointmaster, String statDateM);

}
