package com.dy.service.security;

import com.dy.exception.CUserNotFoundException;
import com.dy.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserDetails loadUserByUsername(String userPK){
        System.out.println("1-----------------------------------------");
        return userRepository.findById(Long.valueOf(userPK)).orElseThrow(CUserNotFoundException::new);
    }
}
