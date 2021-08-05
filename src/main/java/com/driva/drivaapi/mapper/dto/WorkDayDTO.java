package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.model.work.WorkDay;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Getter
@Setter
@NoArgsConstructor
public class WorkDayDTO {
   
   private Long id;
   
   @Positive(message = "instructorId must be a positive digit")
   @NotNull(message = "instructorId can't be null")
   private Long instructorId;
   
   @NotNull(message = "date can't be null")
   private String date;
   
   @Min(message = "min val 1", value = 0)
   @Max(message = "max val 31", value = 24)
   @NotNull(message = "timeStart can't be null")
   private Integer timeStart;
   
   @Min(message = "min val 0", value = 0)
   @Max(message = "max val 24", value = 24)
   @NotNull(message = "timeEnd can't be null")
   private Integer timeEnd;
   
   public WorkDayDTO(WorkDay workDay) {
	  this.id = workDay.getId();
	  this.instructorId = workDay.getInstructorId().getId();
	  this.date = workDay.getDate();
	  this.timeStart = workDay.getTimeStart();
	  this.timeEnd = workDay.getTimeEnd();
   }
}
