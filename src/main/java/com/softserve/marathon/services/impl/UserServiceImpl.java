package com.softserve.marathon.services.impl;

import com.softserve.marathon.dto.user.RegistrationDto;
import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.exceptions.UserNotFoundByIdException;
import com.softserve.marathon.mapper.UserDtoMapper;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Role;
import com.softserve.marathon.model.User;
import com.softserve.marathon.model.enums.RoleConstant;
import com.softserve.marathon.repositories.RoleRepository;
import com.softserve.marathon.repositories.UserRepository;
import com.softserve.marathon.services.MarathonService;
import com.softserve.marathon.services.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.softserve.marathon.model.enums.RoleConstant.ROLE_USER;
import static java.util.stream.Collectors.toList;

@Service
@Transactional
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private UserDtoMapper userDtoMapper;
    private MarathonService marathonService;
    private ModelMapper modelMapper;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

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
    public boolean addUserToMarathon(String email, long marathonId) {
        Marathon marathonEntity = marathonService.getById(marathonId);
        User userEntity = getUserByMail(email);
        marathonEntity.getUsers().add(userEntity);
        userEntity.getMarathons().add(marathonEntity);
        return marathonEntity.getUsers().stream()
                .anyMatch(u -> u.equals(userEntity));

    }

    @Override
    public UserDto registration(RegistrationDto dto) {
        Role roleEntity = roleRepository.findByRoleName(ROLE_USER);
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(Set.of(roleEntity));
        User userEntity = userRepository.save(user);
        return userDtoMapper.convertToDto(userEntity);
    }

    @Override
    public User loadUserByUsername(String mail) throws UsernameNotFoundException {
        User user = getUserByMail(mail);
        if (user == null) throw new UsernameNotFoundException("user not found by email: " + mail);
        return user;
    }

    public boolean isUserHasMarathon(long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByMail((String) authentication.getPrincipal());
        return user.getId() == userId;
    }

    public boolean isUserHaveProgress(long taskId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByMail((String) authentication.getPrincipal());
        return user.getMarathons().stream()
                .anyMatch(marathon -> marathon.getSprints().stream()
                        .anyMatch(sprint -> sprint.getTasks().stream()
                                .anyMatch(task -> task.getId() == taskId)));
    }

    public boolean isUserHasSprint(long marathonId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByMail((String) authentication.getPrincipal());
        return user.getMarathons().stream()
                .anyMatch(marathon -> marathon.getId() == marathonId);
    }

    public boolean isUserHaveTask(long sprintId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByMail((String) authentication.getPrincipal());
        return user.getMarathons().stream()
                .anyMatch(marathon -> marathon.getSprints().stream()
                        .anyMatch(sprint -> sprint.getId() == sprintId));
    }

    public boolean isUsersEmail(String email) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByMail((String) authentication.getPrincipal());
        return email.equals(user.getEmail());
    }

    public boolean isUsersId(long userId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = getUserByMail((String) authentication.getPrincipal());
        return userId == user.getId();
    }
}
