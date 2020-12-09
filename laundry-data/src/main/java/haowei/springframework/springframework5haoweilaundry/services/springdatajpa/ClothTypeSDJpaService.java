package haowei.springframework.springframework5haoweilaundry.services.springdatajpa;

import haowei.springframework.springframework5haoweilaundry.model.ClothType;
import haowei.springframework.springframework5haoweilaundry.repositories.ClothTypeRepository;
import haowei.springframework.springframework5haoweilaundry.services.ClothTypeService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClothTypeSDJpaService implements ClothTypeService {
    private final ClothTypeRepository clothTypeRepository;

    public ClothTypeSDJpaService(ClothTypeRepository clothTypeRepository) {
        this.clothTypeRepository = clothTypeRepository;
    }

    @Override
    public Set<ClothType> findAll() {
        Set<ClothType> clothTypes = new HashSet<>();
        clothTypeRepository.findAll().forEach(clothTypes::add);
        return clothTypes;
    }

    @Override
    public ClothType findById(Long aLong) {
        return clothTypeRepository.findById(aLong).orElse(null);
    }

    @Override
    public ClothType save(ClothType object) {
        return clothTypeRepository.save(object);
    }

    @Override
    public void delete(ClothType object) {
        clothTypeRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clothTypeRepository.deleteById(aLong);
    }
}
