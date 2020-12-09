package haowei.springframework.springframework5haoweilaundry.services.springdatajpa;

import haowei.springframework.springframework5haoweilaundry.model.Cloth;
import haowei.springframework.springframework5haoweilaundry.repositories.ClothRepository;
import haowei.springframework.springframework5haoweilaundry.services.ClothService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ClothSDJpaService implements ClothService {
    private final ClothRepository clothRepository;

    public ClothSDJpaService(ClothRepository clothRepository) {
        this.clothRepository = clothRepository;
    }

    @Override
    public Set<Cloth> findAll() {
        Set<Cloth> clothes = new HashSet<>();
        clothRepository.findAll().forEach(clothes::add);
        return clothes;
    }

    @Override
    public Cloth findById(Long aLong) {
        return clothRepository.findById(aLong).orElse(null);
    }

    @Override
    public Cloth save(Cloth object) {
        return clothRepository.save(object);
    }

    @Override
    public void delete(Cloth object) {
        clothRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        clothRepository.deleteById(aLong);
    }
}
