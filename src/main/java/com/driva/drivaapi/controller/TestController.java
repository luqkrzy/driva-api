package com.driva.drivaapi.controller;

import com.driva.drivaapi.model.user.old.DateTest;
import com.driva.drivaapi.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/test")
public class TestController {
   
   private final StudentRepository studentRepository;
   
   @GetMapping("/all")
   public String allAccess() {
	  return "Public Content.";
   }
   
   @GetMapping("/admin")
   @PreAuthorize("hasRole('ADMIN')")
   public String userAccess() {
	  return "ADMIN CONTENT.";
   }
   
   @GetMapping("/mod")
   @PreAuthorize("hasRole('MODERATOR')")
   public String moderatorAccess() {
	  return "Moderator Board.";
   }
   
   @GetMapping("/student")
   @PreAuthorize("hasRole('STUDENT')")
   public String studentAccess() {
	  return "STUDENT Board.";
   }
   
   @GetMapping("/instructor")
   @PreAuthorize("hasRole('INSTRUCTOR')")
   public String instructorAccess() {
	  return "Instructor Board.";
   }
   
   @PostMapping("/a")
   public void date(@RequestParam("date") @DateTimeFormat(pattern = "dd.MM.yyyy HH:mm") Date date) {
	  System.out.println(date);
   }
   
   @PostMapping("/b")
   public void localDate(
		   @RequestParam("localDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate localDate) {
	  System.out.println(localDate);
   }
   
   @PostMapping("/c")
   public void dateTime(
		   @RequestParam("localDateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime localDateTime) {
	  System.out.println(localDateTime);
   }
   
   @PostMapping("/time")
   public void timeTest(@RequestBody DateTest datetest) {
	  System.out.println(datetest);
   }
   
   @GetMapping("/custem/{id}")
   List<Object[]> mixQuery(@PathVariable Long id) {
	  return studentRepository.mixQuery(id);
   }
}
