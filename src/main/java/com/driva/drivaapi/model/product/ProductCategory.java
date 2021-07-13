package com.driva.drivaapi.model.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product_category", uniqueConstraints = {
        @UniqueConstraint( name = "product_category_name_unique", columnNames = "name")})
public class ProductCategory {

    @Id
    @SequenceGenerator( name = "product_category_id_sq", sequenceName = "product_category_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_category_id_sq")
    private Integer id;

    @NotBlank(message = "name can't be blank")
    @Size(min = 3, max = 20)
    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @OneToOne(mappedBy = "categoryId")
    private ProductType productType;


}
