package com.driva.drivaapi.model.user;

import com.driva.drivaapi.constant.ValidationRegexConstant;
import com.driva.drivaapi.mapper.dto.InstructorDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.work.WorkDay;
import lombok.AllArgsConstructor;
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
@Table(name = "instructor",
        uniqueConstraints = {
                @UniqueConstraint(name = "instructor_email_unique", columnNames = "email")})
public class Instructor {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_id_sq")
   @SequenceGenerator(name = "instructor_id_sq", sequenceName = "instructor_id_sq", allocationSize = 1)
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
   
   @NotNull(message = "createdBy cant be null")
   @Column(name = "created_by")
   private Long createdBy;
   
   @Column(name = "created_date", columnDefinition = "timestamp default now()")
   private Instant createdDate;
   
   @Column(name = "user_id")
   private Long userId;
   
   @OneToMany(mappedBy = "instructorId", cascade = CascadeType.DETACH)
   @NotFound(action = NotFoundAction.IGNORE)
   private List<Lesson> lessons;
   
   @OneToMany(mappedBy = "instructorId", cascade = CascadeType.DETACH)
   @NotFound(action = NotFoundAction.IGNORE)
   private List<WorkDay> workSchedule;
   
   public Instructor(InstructorDTO instructorDTO) {
      this.createdDate = Instant.now();
      this.firstName = instructorDTO.getFirstName();
      this.lastName = instructorDTO.getLastName();
      this.email = instructorDTO.getEmail();
      this.phoneNumber = Integer.parseInt(instructorDTO.getPhoneNumber());
   }
   
   public Instructor update(InstructorDTO instructorDTO) {
      this.firstName = instructorDTO.getFirstName();
      this.lastName = instructorDTO.getLastName();
      this.email = instructorDTO.getEmail();
      this.phoneNumber = Integer.parseInt(instructorDTO.getPhoneNumber());
      return this;
   }
}
