package com.softserve.marathon.repositories;

import com.softserve.marathon.model.Marathon;
import com.softserve.marathon.model.Role;
import com.softserve.marathon.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;
import java.util.Set;

import static com.softserve.marathon.model.enums.RoleConstant.*;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Test
    void getAllByRoleTest() {

        Role roleUser = new Role();
        roleUser.setRoleName(ROLE_USER);
        testEntityManager.persist(roleUser);

        Role roleAdmin = new Role();
        roleAdmin.setRoleName(ROLE_ADMIN);
        testEntityManager.persist(roleAdmin);

        User user1 = new User();
        user1.setEmail("u1@g.c");
        user1.setFirstName("u1");
        user1.setLastName("u1");
        user1.setPassword("u1");
        user1.setRole(Set.of(roleUser));
        user1.setMarathons(Set.of());
        user1.setProgresses(Set.of());
        testEntityManager.persist(user1);

        User user2 = new User();
        user2.setEmail("u2@g.c");
        user2.setFirstName("u2");
        user2.setLastName("u2");
        user2.setPassword("u2");
        user2.setRole(Set.of(roleAdmin));
        user2.setMarathons(Set.of());
        user2.setProgresses(Set.of());
        testEntityManager.persist(user2);

        User user3 = new User();
        user3.setEmail("u3@g.c");
        user3.setFirstName("u3");
        user3.setLastName("u3");
        user3.setPassword("u3");
        user3.setRole(Set.of(roleUser, roleAdmin));
        user3.setMarathons(Set.of());
        user3.setProgresses(Set.of());
        testEntityManager.persist(user3);

        List<User> expectedUsers = List.of(user1, user3);
        System.out.println("expectedUsers = " + expectedUsers);
        List<User> actualUsers = userRepository.findAllByRoleRoleName(ROLE_USER);
        System.out.println("actualUsers = " + actualUsers);
        assertEquals(expectedUsers, actualUsers);

        List<User> expectedAdmins = List.of(user2, user3);
        System.out.println("expectedAdmins = " + expectedAdmins);
        List<User> actualAdmins = userRepository.findAllByRoleRoleName(ROLE_ADMIN);
        System.out.println("actualAdmins = " + actualAdmins);
        assertEquals(expectedAdmins, actualAdmins);

    }

    @Test
    @Sql("file:src/test/resources/sql/marathon.sql")
    void testUsersByMarathonID() {

        User user = new User();
        user.setId(1L);
        user.setFirstName("test");
        user.setLastName("test");
        user.setEmail("test@gmail.com");
        user.setPassword("test");

        List<User> expected = List.of(user);
        List<User> actual = userRepository.findAllByMarathonsId(1);
        assertEquals(expected, actual);

    }

}