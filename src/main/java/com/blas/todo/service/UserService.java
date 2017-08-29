package com.blas.todo.service;

import com.blas.todo.entity.Role;
import com.blas.todo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("userService")
public class UserService implements UserDetailsService {
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.blas.todo.entity.User user = userRepository.findByUsername(username);
        List<GrantedAuthority> autorithies = buildAuthorities(user.getRole());

        return buildUser(user, autorithies);
    }

    private User buildUser(com.blas.todo.entity.User user, List<GrantedAuthority> authorities) {
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), true, true, true, authorities);
    }

    private List<GrantedAuthority> buildAuthorities(Set<Role> roles) {
        Set<GrantedAuthority> auths = new HashSet<GrantedAuthority>();

        for (Role role : roles) {
            auths.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new ArrayList<GrantedAuthority>(auths);
    }
}
