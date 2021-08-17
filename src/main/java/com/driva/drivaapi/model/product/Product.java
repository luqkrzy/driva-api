package com.driva.drivaapi.model.product;

import com.driva.drivaapi.mapper.dto.StudentProductDTO;
import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.user.Student;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
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
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sq")
   @SequenceGenerator(name = "product_id_sq", sequenceName = "product_id_sq", allocationSize = 1)
   @Column(name = "id", updatable = false)
   private Long id;
   
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
   
   @OneToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
   @JoinColumn(name = "product_type_id", referencedColumnName = "id",
           foreignKey = @ForeignKey(name = "fk_product_type_id"))
   @NotFound(action = NotFoundAction.IGNORE)
   private ProductType productType;
   
   @NotNull(message = "student id can't be null")
   @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER, optional = false)
   @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id",
           foreignKey = @ForeignKey(name = "fk_student_id"))
   @NotFound(action = NotFoundAction.IGNORE)
   private Student studentId;
   
   @OneToMany(mappedBy = "productId", cascade = CascadeType.ALL)
   @NotFound(action = NotFoundAction.IGNORE)
   private List<Lesson> lessons = new ArrayList<>();
   
   public Product update(StudentProductDTO studentProductDTO, ProductType productType) {
      this.productType = productType;
      this.hoursLeft = studentProductDTO.getHoursLeft();
      this.bookOnline = studentProductDTO.getBookOnline();
      this.isPaid = studentProductDTO.getIsPaid();
      this.price = studentProductDTO.getPrice();
      return this;
   }
}
