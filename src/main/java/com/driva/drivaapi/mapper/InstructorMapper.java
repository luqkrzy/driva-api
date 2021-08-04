package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.InstructorDTO;
import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.security.service.impl.UserDetailsImpl;
import com.driva.drivaapi.service.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InstructorMapper {
   
   private final LessonService lessonService;
   
   public InstructorDTO entityToInstructorDTO(Instructor instructor) {
	  final InstructorDTO instructorDTO = new InstructorDTO(instructor);
	  final List<LessonDTO> lessonDTOs = lessonService.findLessonsByInstructorId(instructor.getId());
	  lessonDTOs.forEach(lessonDTO -> lessonDTO.setInstructorInfo(null));
	  instructorDTO.setLessons(lessonDTOs);
	  return instructorDTO;
   }
   
   public List<InstructorDTO> entitiesToInstructorDTOs(List<Instructor> instructors) {
	  if (instructors == null) {
		 return null;
	  } return instructors.stream().filter(Objects::nonNull).map(this::entityToInstructorDTO)
						  .collect(Collectors.toList());
   }
   
   public Instructor instructorDTOtoEntity(InstructorDTO instructorDTO) {
	  UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication()
																		 .getPrincipal();
	  final Instructor instructor = new Instructor(instructorDTO);
	  instructor.setCreatedBy(principal.getId());
	  return instructor;
   }
}
