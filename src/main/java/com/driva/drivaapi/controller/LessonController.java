package com.driva.drivaapi.controller;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.pojo.GeneralLesson;
import com.driva.drivaapi.service.InstructorService;
import com.driva.drivaapi.service.LessonService;
import com.driva.drivaapi.service.ProductService;
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
@RequestMapping("api/lessons")
public class LessonController {
   
   private final LessonService lessonService;
   private final ProductService productService;
   private final InstructorService instructorService;
   
   @GetMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   List<GeneralLesson> getAllLessons() {
      return lessonService.findAllToGeneralLessons();
   }
   
   @GetMapping("/product/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   List<GeneralLesson> getLessonsByProductId(@PathVariable Long id) {
      return lessonService.findAllLessonsByProductId(id);
   }
   
   @GetMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   GeneralLesson getLesson(@PathVariable Long id) {
      return lessonService.findToGeneralLesson(id);
   }
   
   @PostMapping
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.CREATED)
   LessonDTO createLesson(@RequestBody @Valid LessonDTO lessonDTO) {
      final Product product = productService.find(lessonDTO.getProductId());
      final Instructor instructor = instructorService.findEntity(lessonDTO.getInstructorId());
      return lessonService.save(lessonDTO, product, instructor);
   }
   
   @PatchMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(code = HttpStatus.OK)
   LessonDTO updateLesson(@PathVariable Long id, @RequestBody @Valid LessonDTO lessonDTO) {
      final Instructor instructor = instructorService.findEntity(lessonDTO.getInstructorId());
      return lessonService.update(id, lessonDTO, instructor);
   }
   
   @DeleteMapping("/{id}")
   @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
   @ResponseStatus(HttpStatus.NO_CONTENT)
   void deleteLesson(@PathVariable Long id) {
      lessonService.delete(id);
   }
}
