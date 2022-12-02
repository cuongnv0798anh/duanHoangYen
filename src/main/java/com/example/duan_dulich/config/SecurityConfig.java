package com.example.duan_dulich.config;

import com.example.duan_dulich.jwt.JwtFilter;
import com.example.duan_dulich.service.AccountServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false, jsr250Enabled = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private AccountServiceImp accountServiceImp;
    @Autowired
    private JwtFilter jwtFilter;
    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(10);}
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().disable(); // tắt cors
        http.csrf().disable();  // tắt csrf

        http.authorizeRequests()
//                .antMatchers("/api/login","/api/register","/api/test").hasRole("User")
//                .antMatchers("/api/login","/api/register").hasRole("Agency");
//                .antMatchers("/api/**").hasAuthority("Admin")
//                .antMatchers("/api/login","/api/register","/api/tool/*","/api/*")
                .antMatchers("/api/**","/view/**","/api/tour/**")
                .permitAll()
                .anyRequest()
                .authenticated();
        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
    }
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
//        return source;
//    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountServiceImp).passwordEncoder(passwordEncoder());
    }

}

