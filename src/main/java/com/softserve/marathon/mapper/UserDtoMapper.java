package com.softserve.marathon.mapper;

import com.softserve.marathon.dto.marathon.MarathonDto;
import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserDtoMapper implements MapperToDto<User, UserDto>, MapperToEntity<UserDto, User> {

    private ModelMapper modelMapper;
    private ProgressDtoMapper progressDtoMapper;
    private RoleDtoMapper roleDtoMapper;

    @Override
    public UserDto convertToDto(User entity) {
        UserDto dto = modelMapper.map(entity, UserDto.class);
        dto.setMarathonsDto(
                entity.getMarathons().stream()
                        .map(marathon -> modelMapper.map(marathon, MarathonDto.class))
                        .collect(Collectors.toSet()));
        dto.setProgressesDto(
                entity.getProgresses().stream()
                    .map(progress -> progressDtoMapper.convertToDto(progress))
                    .collect(Collectors.toSet()));
        dto.setRoleDto(
                entity.getRole().stream()
                    .map(role -> roleDtoMapper.convertToDto(role))
                    .collect(Collectors.toSet()));
        return dto;
    }

    @Override
    public User convertToEntity(UserDto dto) {
        User entity = modelMapper.map(dto, User.class);
        entity.setMarathons(
                dto.getMarathonsDto().stream()
                        .map(marathonDto -> modelMapper.map(marathonDto, Marathon.class))
                        .collect(Collectors.toSet()));
        entity.setProgresses(
                dto.getProgressesDto().stream()
                        .map(progressDto -> progressDtoMapper.convertToEntity(progressDto))
                        .collect(Collectors.toSet()));
        entity.setRole(
                dto.getRoleDto().stream()
                        .map(roleDto -> roleDtoMapper.convertToEntity(roleDto))
                        .collect(Collectors.toSet()));
        return entity;
    }
}
