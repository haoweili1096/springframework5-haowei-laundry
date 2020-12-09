package haowei.springframework.springframework5haoweilaundry.services;

import haowei.springframework.springframework5haoweilaundry.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
