package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends CrudRepository<Task, Long> {
    List<Task> findAllBySprintId(long sprintId);
}
