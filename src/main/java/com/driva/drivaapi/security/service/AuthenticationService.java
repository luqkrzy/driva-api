package com.driva.drivaapi.security.service;

import com.driva.drivaapi.security.payload.request.LoginRequest;
import com.driva.drivaapi.security.payload.request.SignupRequest;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {

    ResponseEntity<?> authenticateUser(LoginRequest loginRequest);

    ResponseEntity<?> registerUser(SignupRequest signUpRequest);

}
