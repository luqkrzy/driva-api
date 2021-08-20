package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.InstructorDTO;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.service.InstructorService;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("api/instructors")
public class InstructorController {
   
   private final InstructorService instructorService;
   
   @GetMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   List<InstructorDTO> getAllInstructors() {
      return instructorService.findAll();
   }
   
   @GetMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   InstructorDTO getInstructor(@PathVariable Long id) {
      return instructorService.find(id);
   }
   
   @PostMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.CREATED)
   Instructor createInstructor(@RequestBody @Valid InstructorDTO instructor) {
      return instructorService.save(instructor);
   }
   
   @PatchMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.OK)
   InstructorDTO updateInstructor(@PathVariable Long id, @RequestBody @Valid InstructorDTO instructor) {
      return instructorService.update(id, instructor);
   }
   
   @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   void deleteInstructor(@PathVariable Long id) {
      instructorService.delete(id);
   }
   
   @GetMapping("/exist/{email}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   Boolean doesEmailExist(@PathVariable String email) {
      return instructorService.doesEmailExist(email);
   }
}
