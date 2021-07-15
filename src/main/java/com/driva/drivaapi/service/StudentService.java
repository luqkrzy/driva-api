package com.driva.drivaapi.service;

import com.driva.drivaapi.model.user.Student;

import java.util.List;

/**
 * Service Interface for managing {@link Student}.
 */
public interface StudentService {

    /**
     * Save a student.
     *
     * @param student the entity to save.
     * @return the persisted entity.
     */
    Student save(Student student);

    List<Student> findAllStudents();
}
