package com.driva.drivaapi.security.payload.request;

import com.driva.drivaapi.constant.ValidationRegexConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class SignupRequest {
   
   @NotBlank(message = "first name can't be blank")
   @Size(max = 20)
   @Pattern(regexp = ValidationRegexConstant.USERNAME_REGEX, message = "allowed only letters and numbers, no special signs")
   private String username;
   
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
   
   //   @NotNull
   @DateTimeFormat(pattern = "yyyy-MM-dd, HH:mm")
   private LocalDateTime createdDate = LocalDateTime.now();
   
   @NotBlank(message = "password can't be blank")
   @Size(max = 120)
   private String password;
   
   @Size(max = 5)
   private Set<String> roles;
   
   @Override
   public String toString() {
	  return "SignupRequest{" +
			 "username='" + username + '\'' +
			 ", firstName='" + firstName + '\'' +
			 ", lastName='" + lastName + '\'' +
			 ", email='" + email + '\'' +
			 ", phoneNumber='" + phoneNumber + '\'' +
			 ", createdDate=" + createdDate +
			 ", password='" + password + '\'' +
			 ", roles=" + roles +
			 '}';
   }
}
