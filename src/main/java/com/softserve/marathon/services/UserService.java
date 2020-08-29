package com.softserve.marathon.services;

import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.User;
import com.softserve.marathon.model.enums.RoleConstant;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getUserById(long id);
    User createOrUpdateUser(User user);
    List<String> getAllByRole(RoleConstant roleConstant);
    boolean addUserToMarathon(User user, Marathon marathon);
}
