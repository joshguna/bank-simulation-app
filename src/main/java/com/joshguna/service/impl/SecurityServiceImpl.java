package com.joshguna.service.impl;

import com.joshguna.entity.User;
import com.joshguna.entity.common.UserPrincipal;
import com.joshguna.repository.UserRepository;
import com.joshguna.service.SecurityService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    public SecurityServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //we need to get our OWN user from database. how ?
        User user = userRepository.findByUsername(username);

        //return some exception if user doesnt exist
        if (user == null) {
            throw new UsernameNotFoundException("This user does not exist");
        }
        //return user information in as a UserDetails

        return new UserPrincipal(user);
    }
}