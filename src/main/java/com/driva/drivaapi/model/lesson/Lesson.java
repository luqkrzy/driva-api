package com.driva.drivaapi.model.lesson;

import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.User;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lesson")
public class Lesson {

    @Id
    @SequenceGenerator( name = "instructor_id_sq", sequenceName = "instructor_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_id_sq")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_product_id"), nullable = false)
    private Product productId;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "instructor_id", nullable = false, referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_instructor_id"))
    private User instructorId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name ="time_start")
    private Instant timeStart;

    @Column(name ="time_end")
    private Instant timeEnd;
}
