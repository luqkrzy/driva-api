package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class LessonDTO {
   
   @Positive(message = "lesson must be positive digit")
   private Long id;
   
   @NotNull(message = "product id can't be blank")
   @Positive(message = "product id must be positive digit")
   private Long productId;
   
   private Long studentId;
   
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   @NotNull(message = "date id can't be blank")
   private LocalDate date;
   
   @NotNull(message = "time start can't be null")
   private Integer timeStart;
   
   @NotNull(message = "hours count can't be null")
   @Positive(message = "hours count must be a positive digit")
   private Integer hoursCount;
   
   @NotNull(message = "instructor id can't be blank")
   @Positive(message = "instructor id must be positive digit")
   private Long instructorId;
   private String instructorFistName;
   private String instructorLastName;
   private String instructorEmail;
   private String instructorPhoneNumber;
   
   public void setInstructor(Instructor instructor) {
	  this.instructorId = instructor.getId();
	  this.instructorFistName = instructor.getFirstName();
	  this.instructorLastName = instructor.getLastName();
	  this.instructorEmail = instructor.getEmail();
	  this.instructorPhoneNumber = Integer.toString(instructor.getPhoneNumber());
   }
   
   public LessonDTO(Lesson lesson) {
	  this.id = lesson.getId();
	  this.productId = lesson.getProductId().getId();
	  this.instructorId = lesson.getInstructorId().getId();
	  this.date = lesson.getDate();
	  this.timeStart = lesson.getTimeStart();
	  this.hoursCount = lesson.getHoursCount();
	  this.studentId = lesson.getProductId().getStudentId().getId();
   }
}
