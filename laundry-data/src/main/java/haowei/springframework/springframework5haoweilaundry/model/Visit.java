package haowei.springframework.springframework5haoweilaundry.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "visits")
public class Visit extends BaseEntity{
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "cloth_id")
    private Cloth cloth; //one cloth can come to wash many times

    @Column(name = "registrar_job_number")
    private String registrar_job_number;

    @Builder
    public Visit(Long id, LocalDate date, String description, Cloth cloth, String registrar_job_number) {
        super(id);
        this.date = date;
        this.description = description;
        this.cloth = cloth;
        this.registrar_job_number = registrar_job_number;
    }
}
