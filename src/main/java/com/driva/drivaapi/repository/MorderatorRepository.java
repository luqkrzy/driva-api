package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.user.Moderator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MorderatorRepository extends JpaRepository<Moderator, Long> {
}
