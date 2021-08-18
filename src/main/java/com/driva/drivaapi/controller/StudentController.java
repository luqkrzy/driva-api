package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.mapper.dto.StudentProductDTO;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.service.ProductService;
import com.driva.drivaapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

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
   private final ProductService productService;
   
   @GetMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   List<StudentDTO> getAllStudents() {
	  return studentService.findAll();
   }
   
   @GetMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   StudentDTO getStudent(@PathVariable Long id) {
	  return studentService.findStudentDTO(id);
   }
   
   @PostMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.CREATED)
   Student createStudent(@RequestBody @Valid StudentDTO studentDTO) {
	  final Student student = studentService.save(studentDTO);
	  final List<StudentProductDTO> products = studentDTO.getProducts();
	  if (products != null) {
		 productService.saveAll(products, student);
	  }
	  return studentService.find(student.getId());
   }
   
   @PatchMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   StudentDTO getStudent(@PathVariable Long id, @RequestBody @Valid StudentDTO studentDTO) {
	  return studentService.updateStudent(id, studentDTO);
   }
   
   @GetMapping("/exist/{email}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   Boolean doesEmailExist(@PathVariable String email) {
	  return studentService.doesEmailExist(email);
   }
   
   @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   void deleteStudent(@PathVariable Long id) {
	  studentService.delete(id);
   }
}
