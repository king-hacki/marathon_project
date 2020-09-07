package com.softserve.marathon.repositories;

import com.softserve.marathon.model.User;
import com.softserve.marathon.model.enums.RoleConstant;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAll();
    List<User> findAllByRoleRoleName(RoleConstant role);
    List<User> findAllByMarathonsId(long marathonId);
    User findByEmail(String mail);
}
