package com.vup.tess.proc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.vup.tess.model.PointMaster;
import com.vup.tess.repository.PointMasterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PointMasterServiceImpl implements PointMasterService {

	@Autowired
	private PointMasterRepository pointMasterRepository;
	

	@Transactional(readOnly = true)
	public List<PointMaster> findByPointChargeFlag(Sort by, String string) {
		List<PointMaster> pointMaster = pointMasterRepository.findByPointChargeFlag(by,string);
		  
		 return pointMaster;
	}


}
