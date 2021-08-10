package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.pojo.InstructorInfo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LessonInstructorDTO {
   
   @Positive(message = "product must be positive digit")
   private Long id;
   
   @NotNull(message = "product id can't be blank")
   @Positive(message = "product id must be positive digit")
   private Long productId;
   
   @NotNull(message = "instructor id can't be blank")
   @Positive(message = "instructor id must be positive digit")
   private Long instructorId;
   
   @NotNull(message = "date can't be null")
   private LocalDate date;
   
   @NotBlank(message = "date can't be blank")
   private Integer timeStart;
   
   @NotNull(message = "date can't be null")
   @Positive(message = "lesson hours must be positive digit")
   private Integer hoursCount;
   
   private Long studentId;
   
   private InstructorInfo instructorInfo;
   
   public LessonInstructorDTO(Lesson lesson) {
	  this.id = lesson.getId();
	  this.productId = lesson.getProductId().getId();
	  this.instructorId = lesson.getInstructorId().getId();
	  this.date = lesson.getDate();
	  this.timeStart = lesson.getTimeStart();
	  this.hoursCount = lesson.getHoursCount();
	  this.studentId = lesson.getProductId().getStudentId().getId();
   }
}
