package com.vup.tess.proc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.model.EnergyGroup;
import com.vup.tess.repository.EnergyGroupRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EnergyGroupMasterServiceImpl implements EnergyGroupMasterService{
	@Autowired
	private EnergyGroupRepository energyGroupRepository;

	@Transactional(readOnly = true)
	public Optional<EnergyGroup> findById(String energyGroupId) {
		Optional<EnergyGroup> energyGroupIds = energyGroupRepository.findById(energyGroupId);
		return energyGroupIds;
	}
	
	
	
}
