package com.driva.drivaapi.service;

import com.driva.drivaapi.mapper.dto.WorkDayDTO;
import com.driva.drivaapi.model.work.WorkDay;

import java.util.List;

public interface WorkDayService {
   
   List<WorkDayDTO> findAll();
   
   WorkDayDTO findToDTO(Long id);
   
   WorkDay findEntity(Long id);
   
   WorkDayDTO update(WorkDayDTO workday);
   
   void delete(Long id);
   
   WorkDayDTO save(WorkDayDTO workday);
   
   boolean existsById(Long id);
}
