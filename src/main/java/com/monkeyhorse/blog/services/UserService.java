package com.monkeyhorse.blog.services;

import java.util.Collections;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import com.monkeyhorse.blog.Constants;
import com.monkeyhorse.blog.models.User;
import com.monkeyhorse.blog.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @PostConstruct
    protected void initialize() {
        getSuperUser();
    }

    public User createUser(User user){
        System.out.println("UserService-什么时候到这 User createUser");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getSuperUser(){
        System.out.println("UserService-什么时候到这 User getSuperUser");
        User user = userRepository.findByEmail(Constants.DEFAULT_ADMIN_EMAIL);

        if ( user == null) {
            user = createUser(new User(Constants.DEFAULT_ADMIN_EMAIL, Constants.DEFAULT_ADMIN_PASSWORD, User.ROLE_ADMIN));
        }

        return user;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("UserService-什么时候到这  UserDetails loadUserByUsername");
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("user not found");
        }
        return createSpringUser(user);
    }

    public User currentUser(){
        System.out.println("UserService-什么时候到这 void currentUser");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return null;
        }

        String email = ((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername();

        return userRepository.findByEmail(email);
    }

    public boolean changePassword(User user, String password, String newPassword){
        if (password == null || newPassword == null || password.isEmpty() || newPassword.isEmpty())
            return false;

        logger.info("" + passwordEncoder.matches(password, user.getPassword()));
        if (!user.getPassword().equals(passwordEncoder.encode(password)))
            return false;

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        logger.info("User @"+user.getEmail() + " changed password.");

        return true;
    }

    public void signin(User user) {
        System.out.println("UserService-什么时候到这 void signin");
        SecurityContextHolder.getContext().setAuthentication(authenticate(user));
    }

    private Authentication authenticate(User user) {
        System.out.println(" UserService-Authentication authenticate");
        return new UsernamePasswordAuthenticationToken(createSpringUser(user), null, Collections.singleton(createAuthority(user)));
    }

    private org.springframework.security.core.userdetails.User createSpringUser(User user) {
        System.out.println(" UserService-createSpringUser");
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(createAuthority(user)));
    }

    private GrantedAuthority createAuthority(User user) {
        System.out.println("UserService-user.Role()" + user.getRole());
        return new SimpleGrantedAuthority(user.getRole());
    }

}
