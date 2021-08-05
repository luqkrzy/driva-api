package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.InstructorDTO;
import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.mapper.dto.WorkDayDTO;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.model.user.pojo.InstructorLesson;
import com.driva.drivaapi.model.work.WorkDay;
import com.driva.drivaapi.security.service.impl.UserDetailsImpl;
import com.driva.drivaapi.service.LessonService;
import com.driva.drivaapi.service.StudentService;
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
   private final StudentService studentService;
   
   public InstructorDTO entityToInstructorDTO(Instructor instructor) {
	  final InstructorDTO instructorDTO = new InstructorDTO(instructor);
	  final List<LessonDTO> lessonDTOS = lessonService.findLessonsByInstructorId(instructor.getId());
	  final List<WorkDay> workSchedule = instructor.getWorkSchedule();
	  final List<WorkDayDTO> workDayDTOSs = workSchedule.stream().map(WorkDay::toDTO).collect(Collectors.toList());
	  final List<InstructorLesson> instructorLessons = lessonDTOS.stream().map(
			  lessonDTO -> {
				 final Student student = studentService.find(lessonDTO.getStudentId());
				 return new InstructorLesson(lessonDTO, student);
			  }).collect(Collectors.toList());
	  instructorDTO.setLessons(instructorLessons);
	  instructorDTO.setWorkDays(workDayDTOSs);
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
