package com.dy.repository;


import com.dy.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collections;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void whenFindByUid_thenReturnUser(){
        String uid = "chody0116@gmail.com";
        String name = "daeyun";

        userRepository.save(User.builder()
        .uid(uid)
        .password(passwordEncoder.encode("1234"))
        .name(name)
        .roles(Collections.singletonList("ROLE_USER"))
        .build());

        Optional<User> user = userRepository.findByUid(uid);

        assertNotNull(user);
        assertTrue(user.isPresent());
        assertEquals(user.get().getName(),name);

    }

}
