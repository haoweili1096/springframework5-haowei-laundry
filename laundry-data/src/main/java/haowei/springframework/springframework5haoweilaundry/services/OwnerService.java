package haowei.springframework.springframework5haoweilaundry.services;

import haowei.springframework.springframework5haoweilaundry.model.Owner;

import java.util.List;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);

    List<Owner> findAllByLastNameLike(String lastName);
}
