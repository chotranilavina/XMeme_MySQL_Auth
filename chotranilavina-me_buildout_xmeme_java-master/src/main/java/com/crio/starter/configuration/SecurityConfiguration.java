package com.crio.starter.configuration;

import com.crio.starter.repository.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.config.annotation.authentication.builders.*;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

@Autowired
private UserRepository userRepository;

@Autowired
private DataSource dataSource;

@Bean
public PasswordEncoder passwordEncoder() {
        return new  BCryptPasswordEncoder();
}

@Override
public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/register","/register.html","/favicon.ico","**/InsertData","/styles.css","/index.php");
}


@Override
protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder.jdbcAuthentication()
        .passwordEncoder(new BCryptPasswordEncoder())
        .dataSource(dataSource);
}


// @Override
//   protected void configure(HttpSecurity http) throws Exception {
//       http
//        .csrf().disable()
//           .authorizeRequests()
//            .antMatchers("/","/index","/webpublic","/login","/register").permitAll()
//            .antMatchers("/webprivate","/memes").authenticated()
//            .antMatchers("/webadmin").hasRole("ADMIN").and()
//               .formLogin()
//               .loginPage("/login")
//               .permitAll()
//               .and()
//               .logout()
//               .permitAll();
//   }
}
