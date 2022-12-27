package com.geekplus.springsecurity.service;

import com.geekplus.springsecurity.enums.Status;
import com.geekplus.springsecurity.entity.User;
import com.geekplus.springsecurity.repository.UserRepository;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArtemisUserDetailService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    private final GrantedAuthority DEFAULT_ROLE = new SimpleGrantedAuthority("USER");

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByStatusAndName(Status.ENABLE.getStatus(),username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found in db");
        }
        // 2. 设置角色
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        String dbRole = user.getRole();
        if (StringUtils.isNullOrEmpty(dbRole)) {
            grantedAuthorities.add(DEFAULT_ROLE);
        } else {
            String[] roles = dbRole.split(",");
            for (String r : roles) {
                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(r);
                grantedAuthorities.add(grantedAuthority);
            }
        }

        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), grantedAuthorities);
    }
}
