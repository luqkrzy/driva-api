package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.model.user.Student;

import java.util.List;

/**
 * Service Interface for managing {@link Student}.
 */
public interface StudentService {

    /**
     * Save a student.
     *
     * @param studentDTO the entity to save.
     * @return the persisted entity.
     */
    Student save(StudentDTO studentDTO);
    
    List<Student> findAll();
    
    StudentDTO findStudentOld(Long id);
    
    Student find(Long id);
    
    void delete(Long id);
}
