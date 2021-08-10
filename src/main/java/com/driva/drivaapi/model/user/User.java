package com.driva.drivaapi.model.user;

import com.driva.drivaapi.constant.ValidationRegexConstant;
import com.driva.drivaapi.mapper.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_app",
		uniqueConstraints = {
				@UniqueConstraint(name = "user_username_unique", columnNames = "username"),
				@UniqueConstraint(name = "user_email_unique", columnNames = "email")})
public class User {
   
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_sq")
   @SequenceGenerator(name = "user_id_sq", sequenceName = "user_id_sq", allocationSize = 1)
   @Column(name = "id", updatable = false)
   private Long id;
   
   @NotBlank(message = "first name can't be blank")
   @Size(max = 20)
   @Pattern(regexp = ValidationRegexConstant.USERNAME_REGEX)
   @Column(name = "username", nullable = false, length = 20)
   private String username;
   
   @NotBlank(message = "first name can't be blank")
   @Size(max = 50)
   @Pattern(regexp = ValidationRegexConstant.FIRST_LAST_NAME_REGEX)
   @Column(name = "first_name", nullable = false, length = 50)
   private String firstName;
   
   @NotBlank(message = "last name can't be blank")
   @Size(max = 50)
   @Pattern(regexp = ValidationRegexConstant.FIRST_LAST_NAME_REGEX)
   @Column(name = "last_name", nullable = false, length = 50)
   private String lastName;
   
   @Email
   @Pattern(regexp = ValidationRegexConstant.EMAIL_REGEX)
   @NotBlank(message = "email can't be blank")
   @Size(min = 5, max = 50)
   @Column(name = "email", nullable = false, length = 50)
   private String email;
   
   @NotNull(message = "phone number can't be null")
   @Column(name = "phone_number")
   private Integer phoneNumber;
   
   @DateTimeFormat(pattern = "yyyy-MM-dd, HH:mm")
   @Column(name = "created_date", columnDefinition = "timestamp default now()")
   private LocalDateTime createdDate;
   
   @JsonIgnore
   @NotBlank(message = "password can't be blank")
   @Size(max = 120)
   @Column(name = "password", length = 120)
   private String password;
   
   @ManyToMany(fetch = FetchType.LAZY)
   @JoinTable(name = "user_roles_list",
		   joinColumns = @JoinColumn(name = "user_id"),
		   inverseJoinColumns = @JoinColumn(name = "role_id"),
		   foreignKey = @ForeignKey(name = "fk_user_id"),
		   inverseForeignKey = @ForeignKey(name = "fk_role_id"))
   @NotFound(action = NotFoundAction.IGNORE)
   private Set<Role> roles = new HashSet<>();
   
   public User(UserDTO userDTO) {
	  this.id = userDTO.getId();
	  this.username = userDTO.getUsername();
	  this.firstName = userDTO.getFirstName();
	  this.lastName = userDTO.getLastName();
	  this.email = userDTO.getEmail();
	  this.phoneNumber = Integer.parseInt(userDTO.getPhoneNumber());
   }
   
   public User updateUser(UserDTO userDTO) {
	  this.firstName = userDTO.getFirstName();
	  this.lastName = userDTO.getLastName();
	  this.email = userDTO.getEmail();
	  this.phoneNumber = Integer.parseInt(userDTO.getPhoneNumber());
	  return this;
   }
}
