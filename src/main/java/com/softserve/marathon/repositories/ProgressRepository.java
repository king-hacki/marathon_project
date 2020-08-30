package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Progress;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProgressRepository extends CrudRepository<Progress, Long> {
    List<Progress> findAllByUserIdAndTaskSprintMarathonId(long userId, long marathonId);
    List<Progress> findAllByUserIdAndTaskSprintId(long userId, long sprintId);
}
