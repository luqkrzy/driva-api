package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.StudentAlreadyExistException;
import com.driva.drivaapi.exception.StudentNotFoundException;
import com.driva.drivaapi.mapper.StudentMapper;
import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.repository.StudentRepository;
import com.driva.drivaapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
   
   private final StudentRepository studentRepository;
   private final StudentMapper studentMapper;
   
   @Override
   public Student save(StudentDTO studentDTO) {
	  if (studentRepository.existsByEmail(studentDTO.getEmail())) {
		 throw new StudentAlreadyExistException(
				 String.format("Student with email: %s already exist", studentDTO.getEmail()));
	  }
   
	  return studentRepository.save(studentMapper.studentDTOtoEntity(studentDTO));
   }
   
   @Override
   public List<StudentDTO> findAll() {
	  List<Student> all = studentRepository.findAll();
	  return studentMapper.entitiesToStudentDTOs(all);
   }
   
   @Override
   public StudentDTO findStudentDTO(Long id) {
	  return studentMapper.entityToStudentDTO(find(id));
   }
   
   @Override
   public Student find(Long id) {
	  return studentRepository.findById(id).orElseThrow(
			  () -> new StudentNotFoundException("Student not found, id: " + id));
   }
   
   @Override
   public void delete(Long id) {
	  Student student = find(id);
	  studentRepository.delete(student);
   }
   
   @Override
   public Student findNameEmailAndPhone(Long id) {
	  return studentRepository.findNameEmailAndPhone(id);
   }
   
   @Override
   public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
	  final Student student = find(id);
	  final Student updatedStudent = student.update(studentDTO);
	  final Student save = studentRepository.save(updatedStudent);
	  return studentMapper.entityToStudentDTO(save);
   }
   
   @Override
   public Boolean doesEmailExist(String email) {
	  return studentRepository.existsByEmail(email);
   }
}


