package com.vup.tess.proc.service;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.StatMonthly;

public interface StatMonthlyService {

	StatMonthly findByPointMasterAndStatDateMStartingWith(PointMaster tempPointmaster, String statDateM);

}
