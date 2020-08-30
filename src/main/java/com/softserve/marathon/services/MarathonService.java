package com.softserve.marathon.services;

import com.softserve.marathon.model.Marathon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarathonService {
    List<Marathon> getAll();
    Marathon getMarathonById(long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(long id);
}
