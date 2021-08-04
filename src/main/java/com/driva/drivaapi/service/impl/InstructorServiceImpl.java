package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.InstrucorNotFoundException;
import com.driva.drivaapi.mapper.InstructorMapper;
import com.driva.drivaapi.mapper.dto.InstructorDTO;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.repository.InstructorRepository;
import com.driva.drivaapi.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InstructorServiceImpl implements InstructorService {
   
   private final InstructorRepository instructorRepository;
   private final InstructorMapper instructorMapper;
   
   @Override
   public List<InstructorDTO> findAll() {
	  final List<Instructor> all = instructorRepository.findAll();
	  return instructorMapper.entitiesToInstructorDTOs(all);
   }
   
   @Override
   public InstructorDTO find(Long id) {
	  final Instructor instructor = instructorRepository.findById(id).orElseThrow(
			  () -> new InstrucorNotFoundException("Couldn't find Instructor, id: " + id));
	  return instructorMapper.entityToInstructorDTO(instructor);
   }
   
   @Override
   public Instructor save(InstructorDTO instructor) {
	  return instructorRepository.save(instructorMapper.instructorDTOtoEntity(instructor));
   }
   
   @Override
   public Instructor update(InstructorDTO instructor) {
	  return null;
   }
   
   @Override
   public void delete(Long id) {
	  if (instructorRepository.existsById(id)) {
		 instructorRepository.deleteById(id);
	  } else {
		 throw new InstrucorNotFoundException("Couldn't find Instructor, id: " + id);
	  }
   }
}
