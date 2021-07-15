package com.driva.drivaapi.model.user;

import com.driva.drivaapi.config.Constants;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "student")
public class Student {

    @Id
    @SequenceGenerator(name = "student_id_sq", sequenceName = "student_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_sq")
    private Long id;

    // @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    // @JoinColumn(name = "student_id", referencedColumnName = "id",
    //         foreignKey = @ForeignKey(name = "fk_product_id"), nullable = false)
    // private User userId;

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

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "created_date", columnDefinition = "timestamp default now()")
    private Instant createdDate;


}
