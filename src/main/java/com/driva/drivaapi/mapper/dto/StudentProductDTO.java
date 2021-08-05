package com.driva.drivaapi.mapper.dto;

import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.product.ProductType;
import com.driva.drivaapi.model.user.pojo.StudentLesson;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentProductDTO {
    
    @Positive(message = "product must be positive digit")
    private Long id;
    
    @NotNull(message = "product type id can't be null")
    @Positive(message = "product type must be positive digit")
    private Long productTypeId;
    
    @NotNull(message = "product type id can't be blank")
    @Positive(message = "student id must be positive digit")
    private Long studentId;
    
    @Positive(message = "hours should be a positive digit")
    @NotNull(message = "hours left  can't null")
    private Integer hoursLeft;
    
    @Column(name = "book_online")
    private Boolean bookOnline;
    
    @Column(name = "is_paid")
    private Boolean isPaid;
    
    @NotNull(message = "price can't be null")
    @Positive(message = "price should be positive digit")
    private Double price;
    
    private ProductType productType;
    
    private List<StudentLesson> lessons;
    
    public StudentProductDTO(Product product) {
        this.id = product.getId();
        this.productTypeId = product.getProductType().getId();
        this.studentId = product.getStudentId().getId();
        this.hoursLeft = product.getHoursLeft();
        this.bookOnline = product.getBookOnline();
        this.isPaid = product.getIsPaid();
        this.price = product.getPrice();
        this.productType = product.getProductType();
    }
}
