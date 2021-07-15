package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.user.Role;
import com.driva.drivaapi.model.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(UserRole name);

    Boolean existsByName(UserRole role);
}

