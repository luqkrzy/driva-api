package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.mapper.dto.StudentProductDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.security.service.impl.UserDetailsImpl;
import com.driva.drivaapi.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {
   
   private final ProductMapper productMapper;
   private final LessonService lessonService;
   
   public Student studentDTOtoEntity(StudentDTO studentDTO) {
	  
	  UserDetailsImpl principal = (UserDetailsImpl)
			  SecurityContextHolder.getContext().getAuthentication()
								   .getPrincipal();
	  return Student.builder()
					.firstName(studentDTO.getFirstName())
					.lastName(studentDTO.getLastName())
					.email(studentDTO.getEmail())
					.phoneNumber(Integer.parseInt(studentDTO.getPhoneNumber()))
					.createdBy(principal.getId())
					.createdDate(Instant.now())
					.userId(studentDTO.getUserId())
					.build();
   }
   
   public StudentDTO entityToStudentDTO(Student student) {
	  List<Product> products = student.getProducts();
	  List<StudentProductDTO> studentProductDTOS = productMapper.entitiesToStudentProductDTOs(products);
	  for (StudentProductDTO studentProductDTO : studentProductDTOS) {
		 studentProductDTO.setLessons(lessonService.findByProductIdToStudentLesson(studentProductDTO.getId()));
	  }
   
	  StudentDTO studentDTO = new StudentDTO(student);
	  studentDTO.setProducts(studentProductDTOS);
	  return studentDTO;
   }
   
   public List<StudentDTO> entitiesToStudentDTOs(List<Student> students) {
	  return students.stream().filter(Objects::nonNull).map(this::entityToStudentDTO).collect(Collectors.toList());
   }
}
