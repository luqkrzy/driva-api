package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.StudentAlreadyExistException;
import com.driva.drivaapi.exception.StudentNotFoundException;
import com.driva.drivaapi.mapper.ProductMapper;
import com.driva.drivaapi.mapper.StudentMapper;
import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.repository.ProductRepository;
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
    private final ProductRepository productRepository;
    private final StudentMapper studentMapper;
    private final ProductMapper productMapper;

    //    @Override
    //    public StudentDTO save(StudentDTO studentDTO) {
    //        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
    //            throw new StudentAlreadyExistException(String.format("Student with email: %s already exist", studentDTO.getEmail()));
    //        }
    //
    //        Student student = studentRepository.save(studentMapper.studentDTOtoEntity(studentDTO));
    //        List<ProductDTO> productDTOSs = studentDTO.getProducts();
    //        if (productDTOSs != null) {
    //            List<Product> products = productMapper.productDTOsToEntities(productDTOSs);
    //            productRepository.saveAll(products);
    //            student.setProducts(products);
    //        }
    //
    //        return studentMapper.entityToStudentDTO(student);
    //    }

    @Override
    public Student save(StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new StudentAlreadyExistException(String.format("Student with email: %s already exist", studentDTO.getEmail()));
        }

        Student student = studentRepository.save(studentMapper.studentDTOtoEntity(studentDTO));
        List<ProductDTO> productDTOSs = studentDTO.getProducts();
        if (productDTOSs != null) {
            List<Product> products = productMapper.productDTOsToEntities(productDTOSs, student.getId());
            productRepository.saveAll(products);
            student.setProducts(products);
        }
        return student;
    }


    @Override
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public StudentDTO findStudentOld(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found, id: " + id));
        return studentMapper.entityToStudentDTO(student);
    }

    @Override
    public Student findStudent(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student not found, id: " + id));

    }


}
