package com.vup.tess.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.EnergyGroup;

@Repository
public interface EnergyGroupRepository extends JpaRepository<EnergyGroup, String> {

}
