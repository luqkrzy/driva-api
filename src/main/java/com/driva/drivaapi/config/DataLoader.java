package com.driva.drivaapi.config;

import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.Role;
import com.driva.drivaapi.model.user.UserRole;
import com.driva.drivaapi.security.payload.request.SignupRequest;
import com.driva.drivaapi.security.service.AuthenticationService;
import com.driva.drivaapi.service.LessonService;
import com.driva.drivaapi.service.ProductService;
import com.driva.drivaapi.service.ProductTypeService;
import com.driva.drivaapi.service.RoleService;
import com.driva.drivaapi.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class DataLoader {
   
   private final RoleService roleService;
   private final AuthenticationService authenticationService;
   private final ProductTypeService productTypeService;
   private final ProductService productService;
   private final LessonService lessonService;
   private final StudentService studentService;
   
   @Bean
   @Profile("test")
   InitializingBean sendDatabase() {
	  return () -> {
		 System.out.println("LOADING DUMMY DATA!!!!!!");
		 
		 final List<Role> roles = List.of(new Role(UserRole.ROLE_ADMIN),
										  new Role(UserRole.ROLE_STUDENT),
										  new Role(UserRole.ROLE_MODERATOR),
										  new Role(UserRole.ROLE_INSTRUCTOR));
		 roleService.saveAll(roles);
	  
		 final SignupRequest signupRequest =
				 new SignupRequest("admin", "Luq", "Krzy",
								   "luq@wp.pl", "123456789",
								   LocalDateTime.now(), "123456",
								   new HashSet<>(List.of("admin")));
		 authenticationService.registerUser(signupRequest);
	  
		 final ProductType productType = new ProductType(1L, "Nauka jazdy", "Opis", "B", 400.99, 50);
		 productTypeService.save(productType);
		 //		 final StudentDTO build = StudentDTO.builder().lastName("Kowalski").firstName("Jan").email("jan@Wp.pl")
		 //											.phoneNumber("123456").createdBy(1L).build();
		 //		 studentService.save(build);
	  
		 //		 final ProductDTO productDTO = new ProductDTO(1L, 1L, 1L, 20,true, false, 200.99,productType );
		 //		 final StudentDTO studentDTO = new StudentDTO(1L, "Jan", "Kowalski", "jan@wp.pl", "123456789", 1L,
		 //													  Instant.now(), 1L, List.of(productDTO));
	  
		 //		 productService.save(1L, productDTO);
		 //		 final Student savedStudent = studentService.save(studentDTO);
		 //		 final Product save = productService.save(1L, productDTO);
		 //		 final Lesson lesson = new Lesson(1L, 1L, 1L, LocalDate.now(), Instant.now(), Instant.now());
		 //		 lessonService.save(lesson);
	  };
   }
}
