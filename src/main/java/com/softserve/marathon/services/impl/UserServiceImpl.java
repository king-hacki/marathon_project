package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.exceptions.UserNotFoundByIdException;
import com.softserve.marathon.mapper.UserDtoMapper;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.User;
import com.softserve.marathon.model.enums.RoleConstant;
import com.softserve.marathon.repositories.UserRepository;
import com.softserve.marathon.services.MarathonService;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserDtoMapper userDtoMapper;
    private MarathonService marathonService;

    @Override
    public List<UserDto> getAll() {
        return userRepository.findAll().stream()
                .map(user -> userDtoMapper.convertToDto(user))
                .collect(toList());
    }

    @Override
    public List<UserDto> getUsersByMarathonId(long marathonId) {
        return userRepository.findAllByMarathonsId(marathonId).stream()
                .map(user -> userDtoMapper.convertToDto(user))
                .collect(toList());
    }

    @Override
    public User getById(long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundByIdException::new);
    }

    @Override
    public void deleteById(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public User update(UserDto dto) {
        getById(dto.getId());
        return userRepository.save(userDtoMapper.convertToEntity(dto));
    }

    @Override
    public User save(UserDto dto) {
        return userRepository.save(userDtoMapper.convertToEntity(dto));
    }

    @Override
    public User getUserByMail(String userMail) {
        return userRepository.findByEmail(userMail);
    }

    @Override
    public List<User> getAllByRole(RoleConstant roleConstant) {
        return userRepository.findAllByRoleRoleName(roleConstant);
    }

    @Override
    public boolean addUserToMarathon(User user, Marathon marathon) {
        marathonService.getById(marathon.getId());
        User userEntity = getById(user.getId());
        marathon.getUsers().add(userEntity);
        return marathon.getUsers().stream()
                .anyMatch(u -> u.equals(userEntity));

    }


}
