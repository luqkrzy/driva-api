package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.WorkDayDTO;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.work.WorkDay;
import com.driva.drivaapi.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class WorkDayMapper {
   
   private final InstructorService instructorService;
   
   public WorkDay workDayDTOtoEntity(WorkDayDTO workDayDTO) {
	  final Instructor instructor = instructorService.findEntity(workDayDTO.getInstructorId());
	  final WorkDay workDay = new WorkDay(workDayDTO);
	  workDay.setInstructorId(instructor);
	  return workDay;
   }
   
   public List<WorkDay> workDayDTOsToEntities(List<WorkDayDTO> workDayDTO) {
	  if (workDayDTO == null) {
		 return null;
	  } return workDayDTO.stream().filter(Objects::nonNull).map(this::workDayDTOtoEntity)
						 .collect(Collectors.toList());
   }
   
   public WorkDayDTO entityToWorkDayDTO(WorkDay workDay) {
	  return new WorkDayDTO(workDay);
   }
   
   public List<WorkDayDTO> entitiesToWorkDayDTOs(List<WorkDay> workDay) {
	  if (workDay == null) {
		 return null;
	  } return workDay.stream().filter(Objects::nonNull).map(this::entityToWorkDayDTO)
					  .collect(Collectors.toList());
   }
}
