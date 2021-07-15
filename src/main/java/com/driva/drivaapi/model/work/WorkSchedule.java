package com.driva.drivaapi.model.work;


import com.driva.drivaapi.model.user.User;
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
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "work_schedule")
public class WorkSchedule {

    @Id
    @SequenceGenerator( name = "work_id_sq", sequenceName = "work_id_sq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "work_id_sq")
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "fk_instructor_id"), nullable = false)
    private User instructorId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name ="time_start")
    private Instant timeStart;

    @Column(name ="time_end")
    private Instant timeEnd;

    @Override
    public String toString() {
        return "WorkSchedule{" +
                "id=" + id +
                ", instructorId=" + instructorId +
                ", date=" + date +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                '}';
    }
}
