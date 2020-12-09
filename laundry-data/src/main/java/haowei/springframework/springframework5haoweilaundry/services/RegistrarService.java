package haowei.springframework.springframework5haoweilaundry.services;

import haowei.springframework.springframework5haoweilaundry.model.Registrar;

import java.util.Set;

public interface RegistrarService {
    Registrar findById(Long id);

    Registrar save(Registrar vet);

    Set<Registrar> findAll();
}
