package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.InstructorDTO;
import com.driva.drivaapi.model.user.Instructor;

import java.util.List;

public interface InstructorService {
   List<InstructorDTO> findAll();
   
   InstructorDTO find(Long id);
   
   Instructor findEntity(Long id);
   
   Instructor save(InstructorDTO instructor);
   
   InstructorDTO update(Long id, InstructorDTO instructor);
   
   void delete(Long id);
   
   Boolean doesEmailExist(String email);
}
