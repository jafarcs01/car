package com.car;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("jafar").password(passwordEncoder().encode("jafarpwd")).roles("USER").and()
                .withUser("sadiq").password(passwordEncoder().encode("sadiq1")).roles("admin");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.GET, "/cars").hasRole("USER")
                .antMatchers(HttpMethod.GET, "/cars/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/cars/**").hasRole("USER")
                .antMatchers(HttpMethod.POST, "/cars").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/cars/update/**").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/cars/delete/**").hasRole("ADMIN")
                .and()
                .csrf().disable()
                .formLogin().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
