package com.driva.drivaapi.model.user;

import com.driva.drivaapi.constant.ValidationRegexConstant;
import com.driva.drivaapi.mapper.dto.StudentDTO;
import com.driva.drivaapi.model.product.Product;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "student",
        uniqueConstraints = {
                @UniqueConstraint(name = "student_email_unique", columnNames = "email")})
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_sq")
    @SequenceGenerator(name = "student_id_sq", sequenceName = "student_id_sq", allocationSize = 1)
    @Column(name = "id", updatable = false)
    private Long id;

    @NotBlank(message = "first name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = ValidationRegexConstant.FIRST_LAST_NAME_REGEX)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = ValidationRegexConstant.FIRST_LAST_NAME_REGEX)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Email
    @Pattern(regexp = ValidationRegexConstant.EMAIL_REGEX)
    @NotBlank(message = "email can't be blank")
    @Size(min = 5, max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;
    
    @NotNull(message = "phone number can't be null")
    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;
    
    @Column(name = "created_by")
    private Long createdBy;
    
    @Column(name = "created_date", columnDefinition = "timestamp default now()")
    private Instant createdDate;
    
    @Column(name = "user_id")
    private Long userId;
    
    @OneToMany(mappedBy = "studentId", cascade = CascadeType.ALL)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<Product> products;
    
    public Student update(StudentDTO studentDTO) {
        this.firstName = studentDTO.getFirstName();
        this.lastName = studentDTO.getLastName();
        this.email = studentDTO.getEmail();
        this.phoneNumber = Integer.parseInt(studentDTO.getPhoneNumber());
        return this;
    }
}

