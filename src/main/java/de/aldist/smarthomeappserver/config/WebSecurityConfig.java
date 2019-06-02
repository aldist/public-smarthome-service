package de.aldist.smarthomeappserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.AccessDeniedHandler;

/*
The WebSecurityConfig class extends WebSecurityConfigurerAdapter to provide custom security
configuration.

Following beans are configured and instantiated in this class:
- JwtTokenFilter
- PasswordEncoder
Also, inside WebSecurityConfig#configure(HttpSecurity http) method we'll configure patterns to
define protected/unprotected API endpoints. Please note that we have disabled CSRF protection
because we
are not using Cookies.
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
          .authorizeRequests().anyRequest().authenticated()
        .and()
          .exceptionHandling().accessDeniedHandler(accessDeniedHandler())
        .and()
          .csrf().disable();
  }

  @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
  @Override
  public AuthenticationManager authenticationManagerBean() throws Exception {
    return super.authenticationManagerBean();
  }

  @Bean
  public AccessDeniedHandler accessDeniedHandler() {
    return new CustomAccessDeniedHandler();
  }
}
