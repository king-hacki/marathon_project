package com.softserve.marathon.services;

import com.softserve.marathon.dto.user.UserDto;
import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.User;
import com.softserve.marathon.model.enums.RoleConstant;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends CrudService<User, UserDto>, UserDetailsService {
    List<UserDto> getAll();
    List<UserDto> getUsersByMarathonId(long marathonId);
    User getUserByMail(String userMail);
    List<User> getAllByRole(RoleConstant roleConstant);
    boolean addUserToMarathon(User user, Marathon marathon);
}
