package com.driva.drivaapi.security.service.impl;

import com.driva.drivaapi.exception.UserRoleNotFoundException;
import com.driva.drivaapi.mapper.UserMapper;
import com.driva.drivaapi.mapper.dto.UserDTO;
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
import com.driva.drivaapi.security.service.AuthenticationService;
import com.driva.drivaapi.util.CapitalizeFirstLetter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationServiceImpl implements AuthenticationService {
   
   public static final String JWT_TOKEN_HEADER = "Jwt-Token";
   private final AuthenticationManager authenticationManager;
   private final UserRepository userRepository;
   private final RoleRepository roleRepository;
   private final PasswordEncoder encoder;
   private final JwtUtils jwtUtils;
   private final UserMapper userMapper;
   
   public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
	  Authentication authentication = authenticationManager.authenticate(
			  new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	  SecurityContextHolder.getContext().setAuthentication(authentication);
	  String jwt = jwtUtils.generateJwtToken(authentication);
	  UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
	  List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
									  .collect(Collectors.toList());
	  HttpHeaders httpHeaders = new HttpHeaders();
	  httpHeaders.add(JWT_TOKEN_HEADER, jwt);
	  return ResponseEntity.ok().headers(httpHeaders)
						   .body(new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(),
												 userDetails.getEmail(), roles));
   }
   
   public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequest signUpRequest) {
	  if (userRepository.existsByUsername(signUpRequest.getUsername())) {
		 return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
	  }
	  
	  if (userRepository.existsByEmail(signUpRequest.getEmail())) {
		 return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
	  }
	  
	  User user = User.builder().username(signUpRequest.getUsername().toLowerCase())
					  .firstName(CapitalizeFirstLetter.capitalize(signUpRequest.getFirstName()))
					  .lastName(CapitalizeFirstLetter.capitalize(signUpRequest.getLastName()))
					  .email(signUpRequest.getEmail().toLowerCase())
					  .phoneNumber(Integer.parseInt(signUpRequest.getPhoneNumber()))
					  .createdDate(signUpRequest.getCreatedDate()).password(encoder.encode(signUpRequest.getPassword()))
					  .build();
	  
	  Set<String> strRoles = signUpRequest.getRoles();
	  Set<Role> roles = new HashSet<>();
	  checkUserRoles(strRoles, roles);
	  user.setRoles(roles);
	  final User save = userRepository.save(user);
	  final UserDTO userDTO = userMapper.entityToUserDTO(save);
	  
	  return ResponseEntity.ok(userDTO);
   }
   
   private void checkUserRoles(Set<String> strRoles, Set<Role> roles) {
	  if (strRoles == null) {
		 throw new UserRoleNotFoundException("Error: role not provided");
	  }
	  
	  strRoles.forEach(role -> {
		 switch (role) {
			case "admin" -> {
			   Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN).orElseThrow(
					   () -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
			   roles.add(adminRole);
			}
			case "moderator" -> {
			   Role modRole = roleRepository.findByName(UserRole.ROLE_MODERATOR).orElseThrow(
					   () -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
			   roles.add(modRole);
			}
			case "instructor" -> {
			   Role instructorRole = roleRepository.findByName(UserRole.ROLE_INSTRUCTOR).orElseThrow(
					   () -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
			   roles.add(instructorRole);
			}
			case "student" -> {
			   Role studentRole = roleRepository.findByName(UserRole.ROLE_STUDENT).orElseThrow(
					   () -> new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role)));
			   roles.add(studentRole);
			}
			default -> throw new UserRoleNotFoundException(String.format("Error: '%s' role is not found.", role));
		 }
	  });
   }
}
