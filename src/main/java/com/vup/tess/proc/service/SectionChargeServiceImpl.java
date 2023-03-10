package com.vup.tess.proc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.model.SectionChargeInfo;
import com.vup.tess.repository.SectionChargeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SectionChargeServiceImpl implements SectionChargeService{
	
	@Autowired
	SectionChargeRepository sectionChargeRepository;
	
	@Transactional(readOnly = true)
	public SectionChargeInfo selectCharge(String factoryGroupId, String energyGroupId, String now, String codeId) {
		SectionChargeInfo sectionChargeInfo = sectionChargeRepository.selectCharge(factoryGroupId, energyGroupId, now, codeId);
		return sectionChargeInfo;
	}

}
