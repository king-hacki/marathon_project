package com.softserve.marathon.services;

import com.softserve.marathon.model.Marathon;

import java.util.List;

public interface MarathonService {
    List<Marathon> getAll();
    Marathon getMarathonById(long id);
    Marathon createOrUpdate(Marathon marathon);
    void deleteMarathonById(long id);
}
