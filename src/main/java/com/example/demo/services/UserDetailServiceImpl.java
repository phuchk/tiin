package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.demo.model.UserEntity;
import com.example.demo.repositories.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findFirstByUsername(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException("k tim thay user name");
        }

        List<GrantedAuthority> gAuthorities = new ArrayList<>();
        GrantedAuthority gAuthority = new SimpleGrantedAuthority("ROLE_" + userEntity.getRole());
        gAuthorities.add(gAuthority);
        UserDetails userDetails = new User(userEntity.getUsername(), userEntity.getPassword(), gAuthorities);
        return userDetails;
    }

}
