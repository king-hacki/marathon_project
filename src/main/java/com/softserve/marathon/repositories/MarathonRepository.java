package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Marathon;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MarathonRepository extends CrudRepository<Marathon, Long> {
    List<Marathon> findAll();
    List<Marathon> findAllByUsersId(long userId);
}
