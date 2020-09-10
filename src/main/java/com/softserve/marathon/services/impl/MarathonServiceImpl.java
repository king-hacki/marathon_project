package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.exception.exceptions.MarathonNotFoundByIdException;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.repositories.MarathonRepository;
import com.softserve.marathon.services.MarathonService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class MarathonServiceImpl implements MarathonService {

    private MarathonRepository marathonRepository;
    private ModelMapper modelMapper;

    @Autowired
    public MarathonServiceImpl(MarathonRepository marathonRepository, ModelMapper modelMapper) {
        this.marathonRepository = marathonRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<MarathonDto> getAll() {
        List<Marathon> allMarathons = marathonRepository.findAll();
        return allMarathons.stream()
                .map(marathon -> modelMapper.map(marathon, MarathonDto.class))
                .collect(toList());
    }

    @Override
    public List<MarathonDto> getByUserId(long userId) {
        return marathonRepository.findAllByUsersId(userId).stream()
                .map(marathon -> modelMapper.map(marathon, MarathonDto.class))
                .collect(toList());
    }

    @Override
    public Marathon getById(long id) {
        return marathonRepository.findById(id).orElseThrow(MarathonNotFoundByIdException::new);
    }

    @Override
    public Marathon save(MarathonDto dto) {
        return marathonRepository.save(modelMapper.map(dto, Marathon.class));
    }

    @Override
    public Marathon update(MarathonDto dto) {
        getById(dto.getId());
        return marathonRepository.save(modelMapper.map(dto, Marathon.class));
    }

    @Override
    public void deleteById(long id) {
        marathonRepository.deleteById(id);
    }
}
