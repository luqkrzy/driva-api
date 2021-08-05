package com.driva.drivaapi.repository;

import com.driva.drivaapi.model.user.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.Collection;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
   
   Boolean existsByEmail(String email);
   
   //    @Query("select s.firstName || '' || s.lastName as fullName, s.email, s.phoneNumber from Student s where s.id =:id")
   @Query("select s.firstName, s.lastName as fullName, s.email, s.phoneNumber from Student s where s.id =:id")
   Student findNameEmailAndPhone(Long id);
   
   @Query("select s,p, l from Student s, Product p, Lesson l where s.id =:id")
   TypedQuery<Collection<?>> mixQuery(Long id);
}
