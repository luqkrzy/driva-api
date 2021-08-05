package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.DateAlreadyExistException;
import com.driva.drivaapi.exception.WorkDayNotFoundException;
import com.driva.drivaapi.mapper.WorkDayMapper;
import com.driva.drivaapi.mapper.dto.WorkDayDTO;
import com.driva.drivaapi.model.work.WorkDay;
import com.driva.drivaapi.repository.WorkDayRepository;
import com.driva.drivaapi.service.WorkDayService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkDayServiceImpl implements WorkDayService {
   
   private final WorkDayRepository workDayRepository;
   private final WorkDayMapper workDayMapper;
   
   @Override
   public List<WorkDayDTO> findAll() {
	  final List<WorkDay> workDays = workDayRepository.findAll();
	  return workDayMapper.entitiesToWorkDayDTOs(workDays);
   }
   
   @Override
   public WorkDayDTO findToDTO(Long id) {
	  final WorkDay workDay = findEntity(id);
	  return workDayMapper.entityToWorkDayDTO(workDay);
   }
   
   @Override
   public WorkDay findEntity(Long id) {
	  return workDayRepository.findById(id).orElseThrow(
			  () -> new WorkDayNotFoundException("Couldn't find work day, id: " + id));
   }
   
   @Override
   public WorkDayDTO update(WorkDayDTO workday) {
	  return null;
   }
   
   @Override
   public void delete(Long id) {
	  if (!existsById(id)) {
		 throw new WorkDayNotFoundException("Couldn't find work day, id: " + id);
	  }
	  workDayRepository.deleteById(id);
   }
   
   @Override
   public WorkDayDTO save(WorkDayDTO workday) {
	  final WorkDay workDayDTO = workDayMapper.workDayDTOtoEntity(workday);
	  if (workDayRepository.findByDate(workday.getDate()) != null) {
		 throw new DateAlreadyExistException("Day already saved");
	  }
	  final WorkDay workDay = workDayRepository.save(workDayDTO);
	  return workDayMapper.entityToWorkDayDTO(workDay);
   }
   
   @Override
   public boolean existsById(Long id) {
	  return workDayRepository.existsById(id);
   }
}
