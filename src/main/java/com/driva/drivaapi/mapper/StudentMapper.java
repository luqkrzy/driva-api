package com.driva.drivaapi.mapper;

import com.driva.drivaapi.mapper.dto.ProductDTO;
import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Student;
import com.driva.drivaapi.repository.ProductRepository;
import com.driva.drivaapi.security.service.impl.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class StudentMapper {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Student studentDTOtoEntity(StudentDTO studentDTO) {

        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return Student.builder()
                .firstName(studentDTO.getFirstName())
                .lastName(studentDTO.getLastName())
                .email(studentDTO.getEmail())
                .phoneNumber(Integer.parseInt(studentDTO.getPhoneNumber()))
                .createdBy(principal.getId())
                .createdDate(Instant.now())
                .build();
    }

    public StudentDTO entityToStudentDTO(Student student) {
        List<Product> products = student.getProducts();
        List<ProductDTO> productDTOs = productMapper.entitiesToProductDTOs(products);
        StudentDTO studentDTO = new StudentDTO(student, productDTOs);
        studentDTO.setProducts(productDTOs);
        return studentDTO;
    }

    public List<StudentDTO> entitiesToStudentDTOs(List<Student> students) {
        return students.stream().filter(Objects::nonNull).map(this::entityToStudentDTO).collect(Collectors.toList());


    }

}
