package com.driva.drivaapi.model.lesson;

import com.driva.drivaapi.mapper.dto.LessonStudentDTO;
import com.driva.drivaapi.model.product.Product;
import com.driva.drivaapi.model.user.Instructor;
import com.fasterxml.jackson.annotation.JsonBackReference;
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

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "lesson")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Lesson {
   
   @Id
   @SequenceGenerator(name = "instructor_id_sq", sequenceName = "instructor_id_sq", allocationSize = 1)
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_id_sq")
   @Column(name = "id")
   private Long id;
   
   @JsonBackReference(value = "productLessons")
   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   @JoinColumn(name = "product_id", referencedColumnName = "id",
		   foreignKey = @ForeignKey(name = "fk_product_id"), nullable = false)
   private Product productId;
   
   //    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, optional = false)
   //    @JoinColumn(name = "instructor_id", nullable = false, referencedColumnName = "id",
   //            foreignKey = @ForeignKey(name = "fk_instructor_id"))
   //       private User instructorId;
   
   //   @JsonBackReference(value = "instructorLesson")
   @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
   @JoinColumn(name = "instructor_id", referencedColumnName = "id",
		   foreignKey = @ForeignKey(name = "fk_instructor_id"))
   private Instructor instructorId;
   
   //   @NotNull(message = "instructor id can't be null")
   //   @Column(name = "instructor_id")
   //   private Long instructorId;
   
   @Column(name = "date")
   private String date;
   
   @Column(name = "time_start")
   private String timeStart;
   
   @Column(name = "time_end")
   private String timeEnd;
   
   public Lesson(LessonStudentDTO lessonStudentDTO) {
	  this.id = lessonStudentDTO.getId();
	  //	  this.instructorId = lessonDTO.getInstructorId();
	  this.date = lessonStudentDTO.getDate();
	  this.timeStart = lessonStudentDTO.getTimeStart();
	  this.timeEnd = lessonStudentDTO.getTimeEnd();
   }
}
