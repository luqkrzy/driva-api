package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
