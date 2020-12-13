package haowei.springframework.springframework5haoweilaundry.bootstrap;

import haowei.springframework.springframework5haoweilaundry.model.ClothType;
import haowei.springframework.springframework5haoweilaundry.repositories.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@Profile({"dev", "prod"})
public class BootStrapMySQL implements ApplicationListener<ContextRefreshedEvent> {
    private final OwnerRepository ownerRepository;
    private final ClothRepository clothRepository;
    private final ClothTypeRepository clothTypeRepository;
    private final VisitRepository visitRepository;
    private final RegistrarRepository registrarRepository;

    public BootStrapMySQL(OwnerRepository ownerRepository, ClothRepository clothRepository, ClothTypeRepository clothTypeRepository, VisitRepository visitRepository, RegistrarRepository registrarRepository) {
        this.ownerRepository = ownerRepository;
        this.clothRepository = clothRepository;
        this.clothTypeRepository = clothTypeRepository;
        this.visitRepository = visitRepository;
        this.registrarRepository = registrarRepository;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        //if clothType data is lost
        if(clothTypeRepository.count() == 0L){
            log.debug("Loading ClothTypes...");
            loadClothTypes();
        }
    }

    private void loadClothTypes(){
        ClothType jacket = new ClothType();
        jacket.setName("Jacket");
        clothTypeRepository.save(jacket);

        ClothType coat = new ClothType();
        coat.setName("Coat");
        clothTypeRepository.save(coat);

        ClothType suit = new ClothType();
        suit.setName("Suit");
        clothTypeRepository.save(suit);

        ClothType shirt = new ClothType();
        shirt.setName("Shirt");
        clothTypeRepository.save(shirt);

        ClothType sweater = new ClothType();
        sweater.setName("Sweater");
        clothTypeRepository.save(sweater);
    }

}
