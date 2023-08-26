/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dmp.configs;

import com.dmp.filters.CustomAccessDeniedHandler;
import com.dmp.filters.JwtAuthenticationTokenFilter;
import com.dmp.filters.RestAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 *
 * @author minhp
 */
@Configuration
@EnableWebSecurity
@EnableTransactionManagement
@ComponentScan(basePackages = {
    "com.dmp.controllers",
    "com.dmp.repository",
    "com.dmp.service",
    "com.dmp.validator"
})
@Order(1)
public class JwtSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter() throws Exception {
        JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter = new JwtAuthenticationTokenFilter();
        jwtAuthenticationTokenFilter.setAuthenticationManager(authenticationManager());
        return jwtAuthenticationTokenFilter;
    }

    @Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }

    @Bean
    public CustomAccessDeniedHandler customAccessDeniedHandler() {
        return new CustomAccessDeniedHandler();
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth)
//            throws Exception {
//        auth.userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin()
//                .usernameParameter("username")
//                .passwordParameter("password");
//
//        http.formLogin().defaultSuccessUrl("/")
//                .failureUrl("/login?error");
//
//        http.logout().logoutSuccessUrl("/login");
//
//        http.exceptionHandling()
//                .accessDeniedPage("/login?accessDenied");

//        http.authorizeRequests().antMatchers("/").permitAll()
//            .antMatchers("/api/**")
//            .access("hasRole('ROLE_ADMIN')");
//        http.csrf().disable();
        // Disable crsf cho đường dẫn /rest/**
        http.csrf().ignoringAntMatchers("/api/**");
        http.authorizeRequests().antMatchers("/api/login/").permitAll()
                .antMatchers("/api/users/").permitAll()
                .antMatchers("/api/check-username/").permitAll()
                .antMatchers("/api/check-email/").permitAll()
                .antMatchers("/api/rules").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers("/api/shifts").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers("/api/payments").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers("/api/prescriptions").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers("/api/rescriptions-medicines").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')")
                .antMatchers("/api/medicines").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')");
//        http.authorizeRequests().antMatchers("/api/shifts/").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')");
//        http.authorizeRequests().antMatchers("/api/payments/").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')");
//        http.authorizeRequests().antMatchers("/api/rules/").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')");
//        http.authorizeRequests().antMatchers("/api/prescriptions/").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')");
//        http.authorizeRequests().antMatchers("/api/rescriptions-medicines/").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')");
//        http.authorizeRequests().antMatchers("/api/medicines/").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE')");
        http.antMatcher("/api/**").httpBasic().authenticationEntryPoint(restServicesEntryPoint()).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/**").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE') or hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.POST, "/api/**").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE') or hasRole('ROLE_PATIENT')")
                .antMatchers(HttpMethod.DELETE, "/api/**").access("hasRole('ROLE_DOCTOR') or hasRole('ROLE_NURSE') or hasRole('ROLE_PATIENT')").and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler());
    }
}
