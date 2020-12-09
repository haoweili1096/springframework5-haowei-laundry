package haowei.springframework.springframework5haoweilaundry.services.springdatajpa;

import haowei.springframework.springframework5haoweilaundry.model.Owner;
import haowei.springframework.springframework5haoweilaundry.repositories.ClothRepository;
import haowei.springframework.springframework5haoweilaundry.repositories.ClothTypeRepository;
import haowei.springframework.springframework5haoweilaundry.repositories.OwnerRepository;
import haowei.springframework.springframework5haoweilaundry.services.OwnerService;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OwnerSDJpaService implements OwnerService {
    private final OwnerRepository ownerRepository;
    private final ClothRepository clothRepository;
    private final ClothTypeRepository clothTypeRepository;

    public OwnerSDJpaService(OwnerRepository ownerRepository, ClothRepository clothRepository, ClothTypeRepository clothTypeRepository) {
        this.ownerRepository = ownerRepository;
        this.clothRepository = clothRepository;
        this.clothTypeRepository = clothTypeRepository;
    }

    @Override
    public Owner findByLastName(String lastName) {
        return ownerRepository.findByLastName(lastName);
    }

    @Override
    public List<Owner> findAllByLastNameLike(String lastName) {
        return ownerRepository.findAllByLastNameLike(lastName);
    }

    @Override
    public Set<Owner> findAll() {
        Set<Owner> owners = new HashSet<>();
        ownerRepository.findAll().forEach(owners::add);
        return owners;
    }

    @Override
    public Owner findById(Long aLong) {
        return ownerRepository.findById(aLong).orElse(null);
    }

    @Override
    public Owner save(Owner object) {
        return ownerRepository.save(object);
    }

    @Override
    public void delete(Owner object) {
        ownerRepository.delete(object);
    }

    @Override
    public void deleteById(Long aLong) {
        ownerRepository.deleteById(aLong);
    }
}
