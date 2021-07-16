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
    StudentDTO save(StudentDTO studentDTO);

    List<StudentDTO> findAllStudents();
}
