package haowei.springframework.springframework5haoweilaundry.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "owners")
public class Owner extends Person{
    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

    @Column(name = "postal_code")
    private String postal_code;

    @Column(name = "telephone")
    private String telephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner")
    private Set<Cloth> clothes = new HashSet<>();

    @Builder
    public Owner(Long id, String firstName, String lastName, String address, String city, String postal_code, String telephone, Set<Cloth> clothes) {
        super(id, firstName, lastName);
        this.address = address;
        this.city = city;
        this.postal_code = postal_code;
        this.telephone = telephone;

        if(clothes != null) {
            this.clothes = clothes;
        }
    }

}
