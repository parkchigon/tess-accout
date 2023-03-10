package com.vup.tess.proc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vup.tess.model.FactoryMaster;
import com.vup.tess.repository.FactoryMasterRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FactoryMasterServiceImpl implements FactoryMasterService {

	@Autowired
	private FactoryMasterRepository factoryMasterRepository;

	@Transactional(readOnly = true)
	public Optional<FactoryMaster> findById(String factoryId) {
		Optional<FactoryMaster> factoryIds = factoryMasterRepository.findById(factoryId);
		return factoryIds;
	}

}
