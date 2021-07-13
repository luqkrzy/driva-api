package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.user.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
