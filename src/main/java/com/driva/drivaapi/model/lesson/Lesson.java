package com.driva.drivaapi.model.lesson;

import com.driva.drivaapi.mapper.dto.LessonDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.springframework.format.annotation.DateTimeFormat;

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
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lesson")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Lesson {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_id_sq")
   @SequenceGenerator(name = "instructor_id_sq", sequenceName = "instructor_id_sq", allocationSize = 1)
   @Column(name = "id", updatable = false)
   private Long id;
   
   @JsonBackReference(value = "productLessons")
   @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
   @JoinColumn(name = "product_id", referencedColumnName = "id",
		   foreignKey = @ForeignKey(name = "fk_product_id"), nullable = false)
   @NotFound(action = NotFoundAction.IGNORE)
   private Product productId;
   
   @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
   @JoinColumn(name = "instructor_id", referencedColumnName = "id",
		   foreignKey = @ForeignKey(name = "fangular send date to apik_instructor_id"))
   @NotFound(action = NotFoundAction.IGNORE)
   private Instructor instructorId;
   
   @Column(name = "date")
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
   private LocalDate date;
   
   @Column(name = "time_start")
   private Integer timeStart;
   
   @Column(name = "hours_count")
   private Integer hoursCount;
   
   public Lesson(LessonDTO lessonDTO) {
      this.id = lessonDTO.getId();
      this.date = lessonDTO.getDate();
      this.hoursCount = lessonDTO.getHoursCount();
      this.timeStart = lessonDTO.getTimeStart();
   }
   
   public Lesson update(LessonDTO lessonDTO) {
      this.date = lessonDTO.getDate();
      this.hoursCount = lessonDTO.getHoursCount();
      this.timeStart = lessonDTO.getTimeStart();
      return this;
   }
}
