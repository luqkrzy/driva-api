package com.driva.drivaapi.security.payload.request;

import com.driva.drivaapi.config.Constants;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "first name can't be blank")
    @Size(max = 20)
    @Pattern(regexp = Constants.USERNAME_REGEX ,message="allowed only letters and numbers, no special signs")
    private String username;

    @NotBlank(message = "first name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = Constants.FIRST_LAST_NAME_REGEX,message="allowed only letters")
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = Constants.FIRST_LAST_NAME_REGEX ,message="allowed only letters")
    private String lastName;

    @Email
    @NotBlank(message = "email can't be blank")
    @Size(min = 5, max = 50)
    private String email;

    @NotNull(message = "phone number can't be null")
    private Integer phoneNumber;

    @NotBlank(message = "password can't be blank")
    @Size(max = 120)
    private String password;

    @Size(max = 3)
    private Set<String> roles;

    @Override
    public String toString() {
        return "SignupRequest{" +
                "username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                // ", phoneNumber=" + phoneNumber +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
