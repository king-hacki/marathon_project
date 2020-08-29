package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Progress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgressRepository extends CrudRepository<Progress, Long> {
}
