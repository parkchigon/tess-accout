package com.vup.tess.proc.service;

import java.util.Optional;

import com.vup.tess.model.FactoryMaster;

public interface FactoryMasterService {

	public Optional<FactoryMaster> findById(String factoryId);
}
