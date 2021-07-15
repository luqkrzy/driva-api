package com.driva.drivaapi.model.user;

import com.driva.drivaapi.config.Constants;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.work.WorkSchedule;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user_app",
        uniqueConstraints = {
                @UniqueConstraint(name = "user_username_unique" , columnNames = "username"),
                @UniqueConstraint( name = "user_email_unique", columnNames = "email")})
public class User {

    @Id
    @SequenceGenerator( name = "user_id_sq", sequenceName = "user_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_sq")
    private Long id;

    @NotBlank(message = "first name can't be blank")
    @Size(max = 20)
    @Pattern(regexp = Constants.USERNAME_REGEX)
    @Column(name = "username", nullable = false, length = 20)
    private String username;

    @NotBlank(message = "first name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = Constants.FIRST_LAST_NAME_REGEX)
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "last name can't be blank")
    @Size(max = 50)
    @Pattern(regexp = Constants.FIRST_LAST_NAME_REGEX)
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Email
    @Pattern(regexp = Constants.EMAIL_REGEX)
    @NotBlank(message = "email can't be blank")
    @Size(min = 5, max = 50)
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @NotNull(message = "phone number can't be null")
    @Column(name = "phone_number", nullable = false)
    private Integer phoneNumber;

    @Column(name = "created_date", columnDefinition = "timestamp default now()")
    private Instant createdDate;

    @JsonIgnore
    @NotBlank(message = "password can't be blank")
    @Size(max = 120)
    @Column(name = "password", length = 120, nullable = false)
    private String password;


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles_list",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            foreignKey = @ForeignKey(name = "fk_user_id"),
            inverseForeignKey = @ForeignKey(name = "fk_role_id"))
    private Set<Role> roles = new HashSet<>();

    // @OneToMany(mappedBy = "userId")
    // private Set<Instructor> instructors;
    //
    // @OneToMany(mappedBy = "userId")
    // private Set<Moderator> moderators;
    //
    // @OneToMany(mappedBy = "userId")
    // private Set<Student> students;
    //
    // @OneToMany(mappedBy = "userId")
    // private Set<Admin> admins;

    @OneToMany(mappedBy = "instructorId")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "instructorId")
    private List<WorkSchedule> workSchedules;


}
