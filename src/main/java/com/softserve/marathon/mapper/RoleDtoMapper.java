package com.softserve.marathon.mapper;

import com.softserve.marathon.dto.Role.RoleDto;
import com.softserve.marathon.model.Role;
import com.softserve.marathon.model.enums.RoleConstant;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RoleDtoMapper implements MapperToDto<Role, RoleDto>, MapperToEntity<RoleDto, Role> {

    private ModelMapper modelMapper;


    @Override
    public RoleDto convertToDto(Role entity) {
        RoleDto dto = modelMapper.map(entity, RoleDto.class);
        dto.setName(entity.getRoleName().name());
        return dto;
    }

    @Override
    public Role convertToEntity(RoleDto dto) {
        Role role = modelMapper.map(dto,Role.class);
        role.setRoleName(RoleConstant.valueOf(dto.getName()));
        return role;
    }
}
