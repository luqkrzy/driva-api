package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.constant.ValidationRegexConstant;
import com.driva.drivaapi.model.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    @NotNull
    @Positive(message = "id must be positive integer")
    private Long id;

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
    
    @DateTimeFormat(pattern = "yyyy-MM-dd, HH:mm")
    private LocalDateTime createdDate;
    
    @Size(max = 5)
    private Set<String> roles;
    
    public UserDTO(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phoneNumber = Integer.toString(user.getPhoneNumber());
        this.createdDate = user.getCreatedDate();
        this.roles = user.getRoles().stream().map(u -> u.getName().name()).collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", createdDate=" + createdDate +
                ", roles=" + roles +
                '}';
    }
}
