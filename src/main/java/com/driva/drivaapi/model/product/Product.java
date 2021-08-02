package com.driva.drivaapi.model.product;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.Student;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Product {

    @Id
    @SequenceGenerator(name = "product_id_sq", sequenceName = "product_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sq")
    @Column(name = "id", columnDefinition = "BIGSERIAL")
    private Long id;

    //    @JsonBackReference(value = "prodType")
    //    @NotNull(message = "product type id can't be blank")
    //    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    //    @JoinColumn(name = "product_type_id", referencedColumnName = "id",
    //            foreignKey = @ForeignKey(name = "fk_product_type_id"), nullable = false)
    //    private ProductType productTypeId;

    @JsonBackReference(value = "studentProducts")
    @NotNull(message = "student id can't be null")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_id"))
    private Student studentId;

    @Positive(message = "hours should be a positive digit")
    @NotNull(message = "hours left  can't null")
    @Column(name = "hours_left", nullable = false)
    private Integer hoursLeft;

    @Column(name = "book_online")
    private Boolean bookOnline;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @NotNull(message = "price can't be null")
    @Positive(message = "price should be positive digit")
    @Column(name = "price", nullable = false)
    private Double price;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "product_type_id", referencedColumnName = "id")
    private ProductType productType;

    @JsonManagedReference
    @OneToMany(mappedBy = "productId")
    private List<Lesson> lessons = new ArrayList<>();


}
