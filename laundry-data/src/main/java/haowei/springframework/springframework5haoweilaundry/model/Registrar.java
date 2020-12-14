package haowei.springframework.springframework5haoweilaundry.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "registrars")
public class Registrar extends Person{
    @Column(name = "job_number")
    private String job_number;

    @Builder
    public Registrar(Long id, String firstName, String lastName, String job_number) {
        super(id, firstName, lastName);
        this.job_number = job_number;
    }
}
