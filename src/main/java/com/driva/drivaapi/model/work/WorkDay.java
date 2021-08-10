package com.driva.drivaapi.model.work;

import com.driva.drivaapi.mapper.dto.WorkDayDTO;
import com.driva.drivaapi.model.user.Instructor;
import lombok.AllArgsConstructor;
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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_day",
		uniqueConstraints = {
				@UniqueConstraint(name = "work_day_unique", columnNames = "date")
		})
public class WorkDay {
   
   @Id
   @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_id_sq")
   @SequenceGenerator(name = "work_id_sq", sequenceName = "work_id_sq", allocationSize = 1)
   @Column(name = "id", updatable = false)
   private Long id;
   
   @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
   @JoinColumn(name = "instructor_id", referencedColumnName = "id",
		   foreignKey = @ForeignKey(name = "fk_instructor_id"), nullable = false)
   @NotFound(action = NotFoundAction.IGNORE)
   private Instructor instructorId;
   
   @NotBlank(message = "timeEnd can't be null")
   @Column(name = "date", nullable = false)
   private String date;
   
   @Min(message = "min val 0", value = 0)
   @Max(message = "max val 24", value = 24)
   @NotNull(message = "timeStart can't be null")
   @Column(name = "time_start", nullable = false)
   private Integer timeStart;
   
   @Min(message = "min val 0", value = 0)
   @Max(message = "max val 24", value = 24)
   @NotNull(message = "timeEnd can't be null")
   @Column(name = "time_end", nullable = false)
   private Integer timeEnd;
   
   public WorkDay(WorkDayDTO workDayDTO) {
	  this.id = workDayDTO.getId();
	  this.date = workDayDTO.getDate();
	  this.timeStart = workDayDTO.getTimeStart();
	  this.timeEnd = workDayDTO.getTimeEnd();
   }
   
   public WorkDayDTO toDTO() {
	  return new WorkDayDTO(this);
   }
}
