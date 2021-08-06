package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
   
   Boolean existsByEmail(String email);
   
   //    @Query("select s.firstName || '' || s.lastName as fullName, s.email, s.phoneNumber from Student s where s.id =:id")
   @Query("select s.firstName, s.lastName as fullName, s.email, s.phoneNumber from Student s where s.id =:id")
   Student findNameEmailAndPhone(Long id);
   
   @Query("select s,p, l from Student s, Product p, Lesson l where s.id =:id")
   List<Object[]> mixQuery(Long id);
}
