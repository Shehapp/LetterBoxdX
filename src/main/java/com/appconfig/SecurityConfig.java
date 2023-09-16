package com.appconfig;

import com.service.ConfirmationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;

@EnableWebSecurity(debug = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .and()
                .authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                        .antMatchers(
                           "/",
                           "/home",
                           "/register",
                           "/movie/*",
                           "/movie",
                           "/profile/*",
                           "/profile/*/*",
                           "/review/*",
                                "/login",
                           "/confirm/**",
                           "/UrlToReach/**")
                        .permitAll()
                .and()
                      .formLogin()
                             .loginPage("/login")
                             .failureHandler(authenticationFailureHandler()).permitAll()
                .and()
                      .logout()
                .and()
                      .exceptionHandling()
                              .accessDeniedPage("/access_denied")
                .and()
                      .rememberMe()
                              .userDetailsService(userDetailsService)
                              .tokenRepository(jdbcTokenRepository())
                              .tokenValiditySeconds(24 * 30 * 60)
                .and()
                .authorizeRequests()
                .anyRequest().authenticated();


    }


    @Bean
    AuthenticationFailureHandler authenticationFailureHandler(){
        return new AuthenticationFailureHandler() {
            @Autowired
            ConfirmationTokenService confirmationTokenService;
            @Override
            public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
                if(exception.getClass().isAssignableFrom(DisabledException.class)){
                    String userName=request.getParameter("username");
                    confirmationTokenService.save(userName);
                    response.sendRedirect("/login?confirm");
                }else
                    response.sendRedirect("/login?error");
            }
        };
    }

    @Override
    public void configure(WebSecurity web)  {
        web
                .ignoring()
                .antMatchers("/resources/**");
    }

    @Bean
    JdbcTokenRepositoryImpl jdbcTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

}
