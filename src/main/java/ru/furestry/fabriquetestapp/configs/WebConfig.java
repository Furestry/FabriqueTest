package ru.furestry.fabriquetestapp.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Class for configure security settings
 *
 * @author Sevler
 */
@Configuration
@EnableWebSecurity
public class WebConfig extends WebSecurityConfigurerAdapter {

    @Value("${admin.password}")
    private String password;

    /**
     * Override configure for security settings
     *
     * @param http security settings
     * @throws Exception exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(a -> a
                        .antMatchers("/user**", "/survey**", "/surveys").permitAll()
                        .antMatchers("/admin**").authenticated()
                )
                .logout(l -> l
                        .logoutSuccessUrl("/surveys").permitAll()
                )
                .csrf(c -> c
                        .disable()
                );
    }

    /**
     * Add user admin with configured password
     *
     * @param auth auth manager
     * @throws Exception exception
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("admin").password(password)
                .authorities("ROLE_ADMIN");
    }
}
