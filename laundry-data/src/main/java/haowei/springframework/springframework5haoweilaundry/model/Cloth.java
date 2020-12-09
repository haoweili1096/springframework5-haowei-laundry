package haowei.springframework.springframework5haoweilaundry.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public ClothType getClothType() {
        return clothType;
    }

    public void setClothType(ClothType clothType) {
        this.clothType = clothType;
    }

    public Owner getOwner() {
        return owner;
    }

    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    public String getMade_in() {
        return made_in;
    }

    public void setMade_in(String made_in) {
        this.made_in = made_in;
    }

    public Set<Visit> getVisits() {
        return visits;
    }

    public void setVisits(Set<Visit> visits) {
        this.visits = visits;
    }
}
