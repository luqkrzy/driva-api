package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.model.lesson.Lesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class LessonDTO {
   
   @Positive(message = "product must be positive digit")
   private Long id;
   
   @NotNull(message = "product id can't be blank")
   @Positive(message = "product id must be positive digit")
   private Long productId;
   
   @NotNull(message = "instructor id can't be blank")
   @Positive(message = "instructor id must be positive digit")
   private Long instructorId;
   
   @NotBlank(message = "date can't be blank")
   private String date;
   
   @NotBlank(message = "date can't be blank")
   private String timeStart;
   
   @NotBlank(message = "date can't be blank")
   private String timeEnd;
   
   private InstructorInfo instructorInfo;
   
   public LessonDTO(Lesson lesson) {
      this.id = lesson.getId();
      this.productId = lesson.getId();
      this.instructorId = lesson.getInstructorId();
      this.date = lesson.getDate();
      this.timeStart = lesson.getTimeStart();
      this.timeEnd = lesson.getTimeEnd();
   }
}
