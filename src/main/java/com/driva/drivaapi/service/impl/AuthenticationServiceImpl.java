package com.driva.drivaapi.service.impl;

import com.driva.drivaapi.exception.UserRoleNotFoundException;
import com.driva.drivaapi.model.user.Role;
import com.driva.drivaapi.model.user.User;
import com.driva.drivaapi.model.user.UserRole;
import com.driva.drivaapi.repository.RoleRepository;
import com.driva.drivaapi.repository.UserRepository;
import com.driva.drivaapi.security.jwt.JwtUtils;
import com.driva.drivaapi.security.payload.request.LoginRequest;
import com.driva.drivaapi.security.payload.request.SignupRequest;
import com.driva.drivaapi.security.payload.response.JwtResponse;
import com.driva.drivaapi.security.payload.response.MessageResponse;
import com.driva.drivaapi.security.service.UserDetailsImpl;
import com.driva.drivaapi.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;

    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));

    }

    public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(), encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();
        checkUserRoles(strRoles, roles);
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    private void checkUserRoles(Set<String> strRoles, Set<Role> roles) {
        if (strRoles == null) {
            throw  new UserRoleNotFoundException("Error: role not provided");
        }

        strRoles.forEach(role -> {
            switch (role) {
                case "admin" -> {
                    Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
                            .orElseThrow(() -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
                    roles.add(adminRole);
                }
                case "morderator" -> {
                    Role modRole = roleRepository.findByName(UserRole.ROLE_MODERATOR)
                            .orElseThrow(() -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
                    roles.add(modRole);
                }
                case "instructor" -> {
                    Role instructorRole = roleRepository.findByName(UserRole.ROLE_INSTRUCTOR)
                            .orElseThrow(() -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
                    roles.add(instructorRole);
                }
                case "student" -> {
                    Role studentRole = roleRepository.findByName(UserRole.ROLE_STUDENT)
                            .orElseThrow(() -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
                    roles.add(studentRole);
                }
                default ->
                        throw new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role));
            }
        });
    }
}
