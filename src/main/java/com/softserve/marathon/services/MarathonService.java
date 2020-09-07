package com.softserve.marathon.services;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.model.Marathon;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MarathonService extends CrudService<Marathon, MarathonDto> {
    List<MarathonDto> getAll();
    List<MarathonDto> getByUserId(long userId);
}
