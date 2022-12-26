package org.itmo.highload.usersservice.security;

import org.itmo.highload.usersservice.security.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Lazy
    private JwtFilter jwtFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/customer/register", "/admin/register", "/admin/auth", "/customer/auth", "/userdata/**").permitAll()
//                .antMatchers(HttpMethod.GET,
//                        "/api/v1/categories", "/api/v1/categories/**",
//                        "/api/v1/dishes", "/api/v1/dishes/**",
//                        "/api/v1/recipes", "/api/v1/recipes/**",
//                        "/api/v1/restaurants", "/api/v1/restaurants/**",
//                        "/api/v1/orders", "/api/v1/orders/**").hasAnyRole("DELIVERY", "ADMIN", "CUSTOMER")
//                .antMatchers("/api/v1/delivery", "/api/v1/delivery/**").hasRole("DELIVERY")
//                .antMatchers("/api/v1/customer", "/api/v1/customer/**").hasRole("CUSTOMER")
                .anyRequest().authenticated();
             //   .and()
             //   .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

