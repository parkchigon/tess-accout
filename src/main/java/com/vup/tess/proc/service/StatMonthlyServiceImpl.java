package com.vup.tess.proc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.StatMonthly;
import com.vup.tess.repository.StatMonthlyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatMonthlyServiceImpl implements StatMonthlyService{
	@Autowired
	private StatMonthlyRepository statMonthlyRepository;
	
	@Transactional(readOnly = true)
	public StatMonthly findByPointMasterAndStatDateMStartingWith(PointMaster tempPointmaster, String statDateM) {
		return statMonthlyRepository.findByPointMasterAndStatDateMStartingWith(tempPointmaster, statDateM);
	}

}
