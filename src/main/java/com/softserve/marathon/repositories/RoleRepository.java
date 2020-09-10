package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Role;
import com.softserve.marathon.model.enums.RoleConstant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRoleName(RoleConstant name);
}
