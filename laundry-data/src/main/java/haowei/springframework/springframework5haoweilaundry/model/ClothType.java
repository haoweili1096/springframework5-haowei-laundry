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

}
