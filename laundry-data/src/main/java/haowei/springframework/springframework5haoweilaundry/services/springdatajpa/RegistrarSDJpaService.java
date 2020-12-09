package haowei.springframework.springframework5haoweilaundry.services.springdatajpa;

import haowei.springframework.springframework5haoweilaundry.model.Registrar;
import haowei.springframework.springframework5haoweilaundry.repositories.RegistrarRepository;
import haowei.springframework.springframework5haoweilaundry.services.RegistrarService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class RegistrarSDJpaService implements RegistrarService {
    private final RegistrarRepository registrarRepository;

    public RegistrarSDJpaService(RegistrarRepository registrarRepository) {
        this.registrarRepository = registrarRepository;
    }

    @Override
    public Set<Registrar> findAll() {
        Set<Registrar> registrars = new HashSet<>();
        registrarRepository.findAll().forEach(registrars::add);
        return registrars;
    }

    @Override
    public Registrar findById(Long aLong) {
        return registrarRepository.findById(aLong).orElse(null);
    }

    @Override
    public Registrar save(Registrar object) {
        return registrarRepository.save(object);
    }

    @Override
    public void delete(Registrar object) {
        registrarRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        registrarRepository.deleteById(aLong);
    }
}
