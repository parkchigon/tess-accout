package com.vup.tess.proc.service;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.vup.tess.model.PointMaster;

public interface PointMasterService {

	public List<PointMaster> findByPointChargeFlag(Sort by, String string);
}
