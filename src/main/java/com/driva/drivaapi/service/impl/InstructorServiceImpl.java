package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.InstructorNotFoundException;
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
	  return instructorMapper.entityToInstructorDTO(findEntity(id));
   }
   
   @Override
   public Instructor findEntity(Long id) {
	  return instructorRepository.findById(id).orElseThrow(
			  () -> new InstructorNotFoundException("Couldn't find Instructor, id: " + id));
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
		 throw new InstructorNotFoundException("Couldn't find Instructor, id: " + id);
	  }
   }
}
