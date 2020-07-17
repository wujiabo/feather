package com.wujiabo.opensource.feather.security.service;

import com.wujiabo.opensource.feather.security.dto.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static java.util.Collections.emptyList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        if("1".equals(username)){
            user = new User();
            user.setUsername("1");
            user.setPassword("$2a$10$a8jcitKFDIxVsITG5yd1eutLz9v3LpCOLkvWkvhrtuU5Vko5WyVzm");
        }
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), emptyList());
    }

}
