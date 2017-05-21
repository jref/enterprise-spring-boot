package com.ua.codespace.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@Profile({"dev", "stage", "prod"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, true from user where username=?")
                .authoritiesByUsernameQuery("select username, 'ROLE_USER' from user where username=?");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().and()
                .csrf().disable()
                .httpBasic().and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/users").authenticated()
                .antMatchers("/users/add").authenticated()
                .antMatchers(HttpMethod.POST, "/articles").authenticated()
                .antMatchers("/articles/add").authenticated()
                .antMatchers("/users/me").authenticated()
                .anyRequest().permitAll();
    }

}
