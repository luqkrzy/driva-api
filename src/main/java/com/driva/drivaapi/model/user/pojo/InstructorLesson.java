package com.driva.drivaapi.model.user.pojo;

import com.driva.drivaapi.mapper.dto.LessonDTO;
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
   
   public InstructorLesson(LessonDTO lessonDTO, Student student) {
      this.lessonId = lessonDTO.getId();
      this.productId = lessonDTO.getProductId();
      this.date = lessonDTO.getDate();
      this.timeStart = lessonDTO.getTimeStart();
      this.timeEnd = lessonDTO.getTimeEnd();
      this.studentEmail = student.getEmail();
      this.studentFullName = student.getFirstName() + ' ' + student.getLastName();
   }
}
