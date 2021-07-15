package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.StudentAlreadyExistException;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.repository.StudentRepository;
import com.driva.drivaapi.security.service.impl.UserDetailsImpl;
import com.driva.drivaapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    public Student save(Student student) {
        if (studentRepository.existsByEmail(student.getEmail())) {
            throw new StudentAlreadyExistException(String.format("Student with email: %s already exist", student.getEmail()));
        }
        UserDetailsImpl user = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        student.setCreatedDate(Instant.now());
        student.setCreatedBy(user.getId());
        return studentRepository.save(student);

    }

    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
}
