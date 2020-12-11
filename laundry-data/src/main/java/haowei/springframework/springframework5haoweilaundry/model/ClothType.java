package haowei.springframework.springframework5haoweilaundry.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "types")
public class ClothType extends BaseEntity{
    @Column(name = "name")
    private String name;

    // solve the problem of showing "haowei.springframework.springframework5haoweilaundry.model.ClothType" on the browser
    @Override
    public String toString() {
        return name;
    }
}
