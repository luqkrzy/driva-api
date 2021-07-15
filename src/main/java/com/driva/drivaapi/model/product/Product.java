package com.driva.drivaapi.model.product;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @SequenceGenerator( name = "product_id_sq", sequenceName = "product_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sq")
    private Long id;

    @NotBlank(message = "product type id can't be blank")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_type_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_product_type_id"), nullable = false)
    private ProductType productTypeId;

    @NotNull(message = "student id can't be null")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "student_id_ID", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_student_id"))
    private Student studentId;

    @Positive(message = "hours should be a positive digit")
    @NotNull(message = "hours left  can't null")
    @Column(name = "hours_left", nullable = false)
    private Integer hoursLeft;

    @Column(name = "book_online")
    private Boolean bookOnline;

    @Column(name = "is_paid")
    private Boolean isPaid;

    @OneToMany(mappedBy = "productId")
    private List<Lesson> lessons;

    @NotNull(message = "price can't be null")
    @Positive(message = "price should be positive digit")
    @Column(name = "price",  nullable = false)
    private Double price;



}
