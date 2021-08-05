package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.work.WorkDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkDayRepository extends JpaRepository<WorkDay, Long> {
}
