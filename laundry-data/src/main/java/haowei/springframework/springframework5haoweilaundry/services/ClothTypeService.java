package haowei.springframework.springframework5haoweilaundry.services;

import haowei.springframework.springframework5haoweilaundry.model.ClothType;

import java.util.Set;

public interface ClothTypeService {
    ClothType findById(Long id);

    ClothType save(ClothType clothType);

    Set<ClothType> findAll();
}
