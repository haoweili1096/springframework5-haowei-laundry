package haowei.springframework.springframework5haoweilaundry.repositories;

import haowei.springframework.springframework5haoweilaundry.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
