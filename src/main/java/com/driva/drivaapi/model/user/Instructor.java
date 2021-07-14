package com.driva.drivaapi.model.user;

import com.driva.drivaapi.model.lesson.Lesson;
import com.driva.drivaapi.model.work.WorkSchedule;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "instructor")
public class Instructor {

    @Id
    @SequenceGenerator( name = "instructor_id_sq", sequenceName = "instructor_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "instructor_id_sq")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_product_id"), nullable = false)
    private User userId;


    @OneToMany(mappedBy = "instructorId")
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "instructorId")
    private List<WorkSchedule> workSchedules;

}
