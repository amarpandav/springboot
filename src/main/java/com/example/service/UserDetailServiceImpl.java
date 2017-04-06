package com.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);


    @Autowired
    private AuthenticationManager authenticationManager;


@Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {
        logger.info("GETTING LOGIN DETAILS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        List<GrantedAuthority> gas = new ArrayList<GrantedAuthority>();
        if ("amar".equals(username)) {
            gas.add(new SimpleGrantedAuthority("ADMIN"));
            gas.add(new SimpleGrantedAuthority("USER"));
        } else {
            gas.add(new SimpleGrantedAuthority("USER"));
        }

        String password = "password"; //TODO in reality
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                username, password, true, true, true, true, gas);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        //Use this for autologin -> authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.info(String.format("Login successfully for username: %s and his authorities are %s.", username, usernamePasswordAuthenticationToken.getAuthorities()));
        }

        return userDetails;
    }
}
