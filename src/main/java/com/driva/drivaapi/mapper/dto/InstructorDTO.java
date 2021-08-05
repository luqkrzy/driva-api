package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.constant.ValidationRegexConstant;
import com.driva.drivaapi.model.user.Instructor;
import com.driva.drivaapi.model.user.pojo.InstructorLesson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class InstructorDTO {
   
   private Long id;
   
   @NotBlank(message = "first name can't be blank")
   @Size(max = 50)
   @Pattern(regexp = ValidationRegexConstant.FIRST_LAST_NAME_REGEX, message = "allowed only letters")
   private String firstName;
   
   @NotBlank(message = "last name can't be blank")
   @Size(max = 50)
   @Pattern(regexp = ValidationRegexConstant.FIRST_LAST_NAME_REGEX, message = "allowed only letters")
   private String lastName;
   
   @Email
   @Pattern(regexp = ValidationRegexConstant.EMAIL_REGEX)
   @NotBlank(message = "email can't be blank")
   @Size(min = 5, max = 50)
   private String email;
   
   @Pattern(regexp = ValidationRegexConstant.PHONE_REGEX, message = "phone number should have 9-13 digits without space")
   @NotBlank(message = "phone number can't be blank")
   private String phoneNumber;
   
   private Long createdBy;
   
   private Instant createdDate;
   
   private Long userId;
   
   private List<WorkDayDTO> workDays;
   
   private List<InstructorLesson> lessons;
   
   public InstructorDTO(Instructor instructor) {
      this.id = instructor.getId();
      this.firstName = instructor.getFirstName();
      this.lastName = instructor.getLastName();
      this.email = instructor.getEmail();
      this.phoneNumber = Integer.toString(instructor.getPhoneNumber());
      this.createdBy = instructor.getCreatedBy();
      this.createdDate = instructor.getCreatedDate();
      this.userId = instructor.getUserId();
   }
}
