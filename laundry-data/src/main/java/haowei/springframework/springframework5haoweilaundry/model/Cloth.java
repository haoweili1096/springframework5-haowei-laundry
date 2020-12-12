package haowei.springframework.springframework5haoweilaundry.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clothes")
public class Cloth extends BaseEntity{
    @Column(name = "color")
    private String color;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private ClothType clothType;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @Column(name = "made_in")
    private String made_in;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cloth")
    private Set<Visit> visits = new HashSet<>();

    @Builder
    public Cloth(Long id, String color, ClothType clothType, Owner owner, String made_in, Set<Visit> visits) {
        super(id);
        this.color = color;
        this.clothType = clothType;
        this.owner = owner;
        this.made_in = made_in;

        if(visits != null){
            this.visits = visits;
        }
    }
}
