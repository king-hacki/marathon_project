package com.softserve.marathon.services.impl;

import com.softserve.marathon.exceptions.UserNotFoundByIdException;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.User;
import com.softserve.marathon.model.enums.RoleConstant;
import com.softserve.marathon.repositories.UserRepository;
import com.softserve.marathon.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundByIdException::new);
    }

    @Override
    public User createOrUpdateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<String> getAllByRole(RoleConstant roleConstant) {
        return null;
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        return false;
    }
}
