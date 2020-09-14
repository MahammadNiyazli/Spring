package com.company.service;

import com.company.dao.impl.UserRepository;
import com.company.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailService")
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepo;

    //@Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);

        if(user!=null){
           UserBuilder builder =  org.springframework.security.core.userdetails.User.withUsername(email);
           builder.password(user.getPassword());
           builder.disabled(false);
           String[] arr = new String[]{"USER"};
           builder.authorities(arr);
           return builder.build();
        }else{
            throw new UsernameNotFoundException("User nor found");
        }


    }
}
