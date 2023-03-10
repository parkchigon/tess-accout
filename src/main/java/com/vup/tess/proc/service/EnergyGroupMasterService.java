package com.vup.tess.proc.service;

import java.util.Optional;

import com.vup.tess.model.EnergyGroup;

public interface EnergyGroupMasterService {

	Optional<EnergyGroup> findById(String energyGroupId);

}
