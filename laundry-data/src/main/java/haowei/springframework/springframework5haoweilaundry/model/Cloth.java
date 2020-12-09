package haowei.springframework.springframework5haoweilaundry.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "clothes")
public class Cloth extends BaseEntity{
    @Column(name = "color")
    private String color;

    @ManyToOne
    @Column(name = "type_id")
    private ClothType clothType;

    @ManyToOne
    @Column(name = "owner_id")
    private Owner owner;

    @Column(name = "made_in")
    private String made_in;

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
}
