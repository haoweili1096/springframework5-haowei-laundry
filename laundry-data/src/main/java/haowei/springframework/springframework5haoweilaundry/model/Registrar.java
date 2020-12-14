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
    @Column(name = "employee_number")
    private String employee_number;

    @Builder

    public Registrar(Long id, String firstName, String lastName, String employee_number) {
        super(id, firstName, lastName);
        this.employee_number = employee_number;
    }
}
