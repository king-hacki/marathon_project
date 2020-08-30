package com.softserve.marathon.services.impl;

import com.softserve.marathon.exceptions.MarathonNotFoundByIdException;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.repositories.MarathonRepository;
import com.softserve.marathon.services.MarathonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    private MarathonRepository marathonRepository;

    @Autowired
    public MarathonServiceImpl(MarathonRepository marathonRepository) {
        this.marathonRepository = marathonRepository;
    }

    @Override
    public List<Marathon> getAll() {
        return marathonRepository.findAll();
    }

    @Override
    public Marathon getMarathonById(long id) {
        return marathonRepository.findById(id).orElseThrow(MarathonNotFoundByIdException::new);
    }

    @Override
    public Marathon createOrUpdate(Marathon marathon) {
        return marathonRepository.save(marathon);
    }

    @Override
    public void deleteMarathonById(long id) {
        marathonRepository.deleteById(id);
    }
}
