package com.driva.drivaapi.model.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "user_role", uniqueConstraints = {
        @UniqueConstraint( name = "user_role_unique", columnNames = "name")})
public class Role {

    @Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "user_id_sq")
	@SequenceGenerator(name = "user_role_sq", sequenceName = "user_role_sq", allocationSize = 1)
	@Column(name = "id", updatable = false)
	private Integer id;
   
   @Enumerated(EnumType.STRING)
   @Column(length = 20)
   private UserRole name;
   
   @ManyToMany(mappedBy = "roles")
   @NotFound(action = NotFoundAction.IGNORE)
   private Set<User> user = new HashSet<>();
   
   public Role(UserRole name) {
	  this.name = name;
   }
   
   @Override
   public String toString() {
	  return "Role{" +
			 "id=" + id +
			 ", name=" + name +
			 ", user=" + user +
			 '}';
   }
}
