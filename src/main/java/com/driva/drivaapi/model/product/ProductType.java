package com.driva.drivaapi.model.product;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "product_type")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class ProductType {
   
   @Id
   @SequenceGenerator(name = "product_type_id_sq", sequenceName = "product_type_id_sq", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "product_type_id_sq")
   @Column(name = "id", updatable = false)
   private Long id;
   
   @NotBlank(message = "name can't be blank")
   @Size(min = 3, max = 50)
   @Column(name = "name", nullable = false)
   private String name;
   
   @NotBlank(message = "description can't be blank")
   @Column(name = "description", nullable = false)
   private String description;
   
   @NotBlank(message = "product category can't be blank")
   @Column(name = "product_category", length = 20, nullable = false)
   private String productCategory;
   
   @NotNull(message = "price can't be null")
   @Positive(message = "price should be positive digit")
   @Column(name = "base_price", nullable = false)
   private Double basePrice;
   
   @NotNull(message = "lesson hours can't be null")
   @Positive(message = "hours should be positive digit")
   @Column(name = "lessons_hours", nullable = false)
   private Integer lessonsHours;
   
   public ProductType update(ProductType productType) {
      this.name = productType.getName();
      this.description = productType.getDescription();
      this.productCategory = productType.getProductCategory();
      this.basePrice = productType.getBasePrice();
      this.lessonsHours = productType.getLessonsHours();
      return this;
   }
   
   //    @OneToOne(mappedBy = "productType")
   //    private Product product;
   
   //    @OneToOne
   //    @MapsId
   //    @JoinColumn(name = "product_id")
   //    private Product product;
   
   //    @JsonManagedReference(value = "prodType")
   //    @OneToMany(mappedBy = "productTypeId", cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
   //    private List<Product> products;
   
}
