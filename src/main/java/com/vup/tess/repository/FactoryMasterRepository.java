package com.vup.tess.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vup.tess.model.FactoryMaster;

@Repository
public interface FactoryMasterRepository extends JpaRepository<FactoryMaster, String>  {


}
