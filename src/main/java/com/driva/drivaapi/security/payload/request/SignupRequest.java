package com.driva.drivaapi.security.payload.request;

import com.driva.drivaapi.constant.ValidationRegexConstants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "first name can't be blank")
    @Size(max = 20)
    @Pattern(regexp = ValidationRegexConstants.USERNAME_REGEX, message = "allowed only letters and numbers, no special signs")
    private String username;

    @NotBlank(message = "first name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = ValidationRegexConstants.FIRST_LAST_NAME_REGEX, message = "allowed only letters")
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = ValidationRegexConstants.FIRST_LAST_NAME_REGEX, message = "allowed only letters")
    private String lastName;

    @Email
    @Pattern(regexp = ValidationRegexConstants.EMAIL_REGEX)
    @NotBlank(message = "email can't be blank")
    @Size(min = 5, max = 50)
    private String email;

    @Pattern(regexp = ValidationRegexConstants.PHONE_REGEX, message = "phone number should have 9-13 digits without space")
    @NotBlank(message = "phone number can't be blank")
    private String phoneNumber;

    @NotNull
    private Instant createdDate = Instant.now();

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
