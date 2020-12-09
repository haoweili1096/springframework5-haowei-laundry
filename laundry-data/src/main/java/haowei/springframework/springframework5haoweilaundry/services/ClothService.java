package haowei.springframework.springframework5haoweilaundry.services;

import haowei.springframework.springframework5haoweilaundry.model.Cloth;

import java.util.Set;

public interface ClothService {
    Cloth findById(Long id);

    Cloth save(Cloth pet);

    Set<Cloth> findAll();
}
