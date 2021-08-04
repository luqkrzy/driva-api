package com.driva.drivaapi.model.work;

import com.driva.drivaapi.model.user.Instructor;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_day")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class WorkDay {
    
    @Id
    @SequenceGenerator(name = "work_id_sq", sequenceName = "work_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_id_sq")
    @Column(name = "id", columnDefinition = "BIGSERIAL")
    private Long id;
    
    //    @JsonManagedReference(value = "instructorWorkDay")
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_instructor_id"), nullable = false)
    private Instructor instructorId;
    
    @Min(message = "min val 1", value = 1)
    @Max(message = "max val 31", value = 31)
    @NotNull(message = "timeEnd can't be null")
    @Column(name = "day")
    private Integer day;
    
    @Min(message = "min val 1", value = 0)
    @Max(message = "max val 31", value = 24)
    @NotNull(message = "timeStart can't be null")
    @Column(name = "time_start")
    private Integer timeStart;
    
    @Min(message = "min val 1", value = 0)
    @Max(message = "max val 31", value = 24)
    @NotNull(message = "timeEnd can't be null")
    @Column(name = "time_end")
    private Integer timeEnd;
}
