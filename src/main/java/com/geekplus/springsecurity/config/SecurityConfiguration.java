package com.geekplus.springsecurity.config;

import com.geekplus.springsecurity.entity.Permission;
import com.geekplus.springsecurity.repository.PermissionRepository;
import com.geekplus.springsecurity.service.ArtemisUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

/**
 *
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    ArtemisUserDetailService detailService;

    @Autowired
    PermissionRepository permissionRepository;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        List<Permission> allPermissions = permissionRepository.findAll();
//        for (Permission permission : allPermissions) {
//            http.authorizeRequests().antMatchers(permission.getPermission()).hasRole(permission.getRole());
//        }
        http
                .requestMatchers().anyRequest()
                .and()
                .authorizeRequests()
                .antMatchers("/apollo/**").permitAll()
                .antMatchers("/oauth/**").permitAll();
        http.csrf().disable();
    }

    /**
     * ??????: ???????????????????????????????????????????????? Spring Security ????????????
     **/
    @Override
    public void configure(WebSecurity web) {
        // ?????????????????????????????????
        web.ignoring()
                .antMatchers("/css/**")
                .antMatchers("/404.html")
                .antMatchers("/500.html")
                .antMatchers("/html/**")
                .antMatchers("/js/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(detailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    /**
     * ??????: ?????????????????? BCrypt ????????????
     **/
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    public static void main(String[] args) {
        String encode = new BCryptPasswordEncoder().encode("123456");
        System.out.println(encode);
    }

}
