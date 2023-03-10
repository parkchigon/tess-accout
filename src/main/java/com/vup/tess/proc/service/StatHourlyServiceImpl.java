package com.vup.tess.proc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.model.PointMaster;
import com.vup.tess.model.StatHourly;
import com.vup.tess.repository.StatHourlyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StatHourlyServiceImpl implements StatHourlyService{
	@Autowired
	private StatHourlyRepository statHourlyRepository;


	@Transactional(readOnly = true)
	public List<StatHourly> findByPointMasterAndStatDateHStartingWith(PointMaster tempPointmaster, String statDateM) {
		// TODO Auto-generated method stub
		return statHourlyRepository.findByPointMasterAndStatDateHStartingWith(tempPointmaster,statDateM);
	}

}
