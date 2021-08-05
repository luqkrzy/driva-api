package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.mapper.dto.LessonStudentDTO;
import com.driva.drivaapi.model.user.Student;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstructorLesson {
   
   private Long lessonId;
   private Long productId;
   private String date;
   private String timeStart;
   private String timeEnd;
   private String studentFullName;
   private String studentEmail;
   private String studentPhone;
   
   public InstructorLesson(LessonStudentDTO lessonStudentDTO, Student student) {
      this.lessonId = lessonStudentDTO.getId();
      this.productId = lessonStudentDTO.getProductId();
      this.date = lessonStudentDTO.getDate();
      this.timeStart = lessonStudentDTO.getTimeStart();
      this.timeEnd = lessonStudentDTO.getTimeEnd();
      this.studentEmail = student.getEmail();
      this.studentFullName = student.getFirstName() + ' ' + student.getLastName();
   }
}
