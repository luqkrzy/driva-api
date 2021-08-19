package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class GeneralLesson {
   
   private Long id;
   private Long productId;
   private LocalDate date;
   private Integer timeStart;
   private Integer hoursCount;
   
   private Long studentId;
   private String studentFistName;
   private String studentLastName;
   private String studentEmail;
   private String studentPhoneNumber;
   
   private Long instructorId;
   private String instructorFistName;
   private String instructorLastName;
   private String instructorEmail;
   private String instructorPhoneNumber;
   
   public GeneralLesson(Lesson lesson) {
	  this.id = lesson.getId();
	  this.productId = lesson.getProductId().getId();
	  this.date = lesson.getDate();
	  this.timeStart = lesson.getTimeStart();
	  this.hoursCount = lesson.getHoursCount();
   }
   
   public void setInstructor(Instructor instructor) {
	  this.instructorId = instructor.getId();
	  this.instructorFistName = instructor.getFirstName();
	  this.instructorLastName = instructor.getLastName();
	  this.instructorEmail = instructor.getEmail();
	  this.instructorPhoneNumber = Integer.toString(instructor.getPhoneNumber());
   }
   
   public void setStudent(Student student) {
	  this.studentId = student.getId();
	  this.studentFistName = student.getFirstName();
	  this.studentLastName = student.getLastName();
	  this.studentEmail = student.getEmail();
	  this.studentPhoneNumber = Integer.toString(student.getPhoneNumber());
   }
}
