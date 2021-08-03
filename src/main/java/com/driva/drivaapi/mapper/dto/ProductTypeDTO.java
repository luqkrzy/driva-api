package com.driva.drivaapi.mapper.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class ProductTypeDTO {
   
   private Long id;
   
   @NotBlank(message = "name can't be blank")
   @Size(min = 3, max = 50)
   private String name;
   
   @NotBlank(message = "description can't be blank")
   private String description;
   
   @NotBlank(message = "product category can't be blank")
   private Integer productCategory;
   
   @NotNull(message = "price can't be null")
   @Positive(message = "price should be positive digit")
   private Double basePrice;
   
   @NotNull(message = "lesson hours can't be null")
   @Positive(message = "hours should be positive digit")
   private Integer lessonsHours;
}
