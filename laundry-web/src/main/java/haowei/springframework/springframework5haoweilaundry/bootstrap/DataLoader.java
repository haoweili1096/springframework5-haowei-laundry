package haowei.springframework.springframework5haoweilaundry.bootstrap;

import haowei.springframework.springframework5haoweilaundry.model.*;
import haowei.springframework.springframework5haoweilaundry.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final RegistrarService registrarService;
    private final ClothService clothService;
    private final ClothTypeService clothTypeService;
    private final VisitService visitService;

    public DataLoader(OwnerService ownerService, RegistrarService registrarService, ClothService clothService, ClothTypeService clothTypeService, VisitService visitService) {
        this.ownerService = ownerService;
        this.registrarService = registrarService;
        this.clothService = clothService;
        this.clothTypeService = clothTypeService;
        this.visitService = visitService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = clothTypeService.findAll().size();

        if(count == 0){
            loadData();
        }
    }

    private void loadData(){
        ClothType jacket = new ClothType();
        jacket.setName("Jacket");
        ClothType savedJacketClothType = clothTypeService.save(jacket);

        ClothType coat = new ClothType();
        coat.setName("Coat");
        ClothType savedCoatClothType = clothTypeService.save(coat);

        ClothType suit = new ClothType();
        suit.setName("Suit");
        ClothType savedSuitClothType = clothTypeService.save(suit);

        ClothType shirt = new ClothType();
        shirt.setName("Shirt");
        ClothType savedShirtClothType = clothTypeService.save(shirt);

        ClothType sweater = new ClothType();
        sweater.setName("Sweater");
        ClothType savedSweaterClothType = clothTypeService.save(sweater);

        System.out.println("Loaded ClothTypes...");

        Owner owner1 = new Owner();
        owner1.setFirstName("John");
        owner1.setLastName("Smith");
        owner1.setAddress("280 W 30th Street");
        owner1.setCity("Miami");
        owner1.setPostal_code("89165");
        owner1.setTelephone("5818515917");

        Cloth cloth1 = new Cloth();
        cloth1.setClothType(savedJacketClothType);
        cloth1.setOwner(owner1);
        cloth1.setColor("black");
        cloth1.setMade_in("US");
        owner1.getClothes().add(cloth1);

        Cloth cloth2 = new Cloth();
        cloth2.setClothType(savedCoatClothType);
        cloth2.setOwner(owner1);
        cloth2.setColor("brown");
        cloth2.setMade_in("Germany");
        owner1.getClothes().add(cloth2);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        owner2.setFirstName("Michael");
        owner2.setLastName("Taylor");
        owner2.setAddress("270 E 31th Street");
        owner2.setCity("Los Angeles");
        owner2.setPostal_code("90001");
        owner2.setTelephone("2138672653");

        Cloth cloth3 = new Cloth();
        cloth3.setClothType(savedSuitClothType);
        cloth3.setOwner(owner2);
        cloth3.setColor("White");
        cloth3.setMade_in("China");
        owner2.getClothes().add(cloth3);

        ownerService.save(owner2);

        Owner owner3 = new Owner();
        owner3.setFirstName("Albert");
        owner3.setLastName("Mason");
        owner3.setAddress("220 S 2th Street");
        owner3.setCity("San Francisco");
        owner3.setPostal_code("58172");
        owner3.setTelephone("9471903571");

        Cloth cloth4 = new Cloth();
        cloth4.setClothType(savedShirtClothType);
        cloth4.setOwner(owner3);
        cloth4.setColor("Blue");
        cloth4.setMade_in("China");
        owner3.getClothes().add(cloth4);

        Cloth cloth5 = new Cloth();
        cloth5.setClothType(savedSweaterClothType);
        cloth5.setOwner(owner3);
        cloth5.setColor("Green");
        cloth5.setMade_in("Japan");
        owner3.getClothes().add(cloth5);

        ownerService.save(owner3);

        Visit owner1_cloth1_visit = new Visit();
        owner1_cloth1_visit.setCloth(cloth1);
        owner1_cloth1_visit.setDate(LocalDate.now());
        owner1_cloth1_visit.setDescription("Needs Deep Clean");

        visitService.save(owner1_cloth1_visit);

        Visit owner1_cloth2_visit = new Visit();
        owner1_cloth2_visit.setCloth(cloth2);
        owner1_cloth2_visit.setDate(LocalDate.now());
        owner1_cloth2_visit.setDescription("Needs Dry Clean");

        visitService.save(owner1_cloth2_visit);

        Visit owner2_cloth3_visit = new Visit();
        owner2_cloth3_visit.setCloth(cloth3);
        owner2_cloth3_visit.setDate(LocalDate.now());
        owner2_cloth3_visit.setDescription("Stained");

        visitService.save(owner2_cloth3_visit);

        Visit owner3_cloth4_visit = new Visit();
        owner3_cloth4_visit.setCloth(cloth4);
        owner3_cloth4_visit.setDate(LocalDate.now());
        owner3_cloth4_visit.setDescription("Needs Ironing");

        visitService.save(owner3_cloth4_visit);

        Visit owner3_cloth5_visit = new Visit();
        owner3_cloth5_visit.setCloth(cloth5);
        owner3_cloth5_visit.setDate(LocalDate.now());
        owner3_cloth5_visit.setDescription("Needs Water Clean and Dry");

        visitService.save(owner3_cloth5_visit);

        System.out.println("Loaded Owners...");

        Registrar registrar1 = new Registrar();
        registrar1.setFirstName("Justin");
        registrar1.setLastName("Cook");

        registrarService.save(registrar1);

        Registrar registrar2 = new Registrar();
        registrar2.setFirstName("Terry");
        registrar2.setLastName("Butler");

        registrarService.save(registrar2);

        System.out.println("Loaded Registrars...");

    }
}
