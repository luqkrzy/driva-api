package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.config.Constants;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.List;

public class StudentDTO {


    private Long id;

    private List<String> products;

    @NotBlank(message = "first name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = Constants.FIRST_LAST_NAME_REGEX, message = "allowed only letters")
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = Constants.FIRST_LAST_NAME_REGEX, message = "allowed only letters")
    private String lastName;

    @Email
    @Pattern(regexp = Constants.EMAIL_REGEX)
    @NotBlank(message = "email can't be blank")
    @Size(min = 5, max = 50)
    private String email;

    @Pattern(regexp = Constants.PHONE_REGEX, message = "phone number should have 9-13 digits without space")
    @NotBlank(message = "phone number can't be blank")
    private String phoneNumber;

    private Long createdBy;

    private Instant createdDate;


}
