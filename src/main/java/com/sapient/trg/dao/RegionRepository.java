package com.sapient.trg.dao;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sapient.trg.entity.RegionMaster;
import com.sapient.trg.entity.RoleMaster;

@Repository
public interface RegionRepository extends JpaRepository<RegionMaster,Long>{

}