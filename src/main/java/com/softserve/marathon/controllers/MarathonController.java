package com.softserve.marathon.controllers;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.services.MarathonService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/marathon")
@Slf4j
@CrossOrigin("*")
public class MarathonController {

    private MarathonService marathonService;
    private ModelMapper modelMapper;

    @Autowired
    public MarathonController(MarathonService marathonService, ModelMapper modelMapper) {
        this.marathonService = marathonService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/list")
    public ResponseEntity<List<MarathonDto>> getAllMarathons() {
        log.info("get all marathons");
        return ok(marathonService.getAll());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<MarathonDto>> getMarathonByUserId(@PathVariable long userId) {
        log.info("get marathons by user ID: " + userId);
        return ok(marathonService.getByUserId(userId));
    }

    @DeleteMapping("/delete/{marathonId}")
    public ResponseEntity<MarathonDto> delete(@PathVariable long marathonId) {
        log.info("Delete marathon with id: " + marathonId);
        marathonService.deleteById(marathonId);
        return ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<MarathonDto> update(@Valid @RequestBody MarathonDto dto) {
        log.info("update Marathon: " + dto);
        return ok(modelMapper.map(marathonService.update(dto), MarathonDto.class));
    }

    @PostMapping("/save")
    public ResponseEntity<MarathonDto> save(@RequestBody MarathonDto dto) {
        log.info("save Marathon: " + dto);
        return ok(modelMapper.map(marathonService.save(dto), MarathonDto.class));
    }



}
