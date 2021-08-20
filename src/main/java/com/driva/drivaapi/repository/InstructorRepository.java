package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.user.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InstructorRepository extends JpaRepository<Instructor, Long> {
   
   boolean existsById(Long id);
   
   Boolean existsByEmail(String email);
}
