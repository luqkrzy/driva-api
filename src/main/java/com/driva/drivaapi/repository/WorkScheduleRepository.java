package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.work.WorkSchedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkScheduleRepository extends JpaRepository<WorkSchedule, Long> {
}
