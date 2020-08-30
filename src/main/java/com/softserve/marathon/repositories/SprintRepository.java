package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Sprint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends CrudRepository<Sprint, Long> {
    List<Sprint> findAllByMarathonId(long marathonId);
}
