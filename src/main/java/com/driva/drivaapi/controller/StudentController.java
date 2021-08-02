package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * REST controller for managing {@link Student}.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/students")
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    List<Student> getAllStudents() {
        return studentService.findAllStudents();
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    @ResponseStatus(code = HttpStatus.CREATED)
    Student createStudent(@RequestBody @Valid StudentDTO student) {
        return studentService.save(student);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    Student getStudent(@PathVariable Long id) {
        return studentService.findStudent(id);
    }

    //    @GetMapping("/{id}")
    //    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    //    StudentDTO getStudentOld(@PathVariable Long id) {
    //        return studentService.findStudent(id);
    //    }
}
