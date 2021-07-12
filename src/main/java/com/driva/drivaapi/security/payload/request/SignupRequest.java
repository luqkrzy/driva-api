package com.driva.drivaapi.security.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {

    @NotBlank(message = "username shouldn't be blank")
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank(message = "email shouldn't be blank")
    @Size(max = 50)
    @Email(message = "Wrong email format")
    private String email;

    @Size(max = 20)
    private Set<String> roles;

    @NotBlank(message = "password shouldn't be blank")
    @Size(min = 6, max = 40)
    private String password;


}
