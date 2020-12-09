package haowei.springframework.springframework5haoweilaundry.model;

public class Cloth {
    private ClothType clothType;
    private Owner owner;

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
}
