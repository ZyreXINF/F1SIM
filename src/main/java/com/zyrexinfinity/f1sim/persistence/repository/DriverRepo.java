package com.zyrexinfinity.f1sim.persistence.repository;

import com.zyrexinfinity.f1sim.persistence.entity.DriverEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepo extends JpaRepository<DriverEntity, Long> {
    List<DriverEntity> findByDriverIdBetween(long startId, long endId);
}
