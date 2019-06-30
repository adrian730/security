package spring.security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails moderator = User.withDefaultPasswordEncoder()
                .username("moderator")
                .password("moderator1")
                .roles("MODERATOR")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin1")
                .roles("ADMIN")
                .build();



        return new InMemoryUserDetailsManager(moderator, admin);



    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);

        http.httpBasic().and().authorizeRequests()
                .antMatchers(HttpMethod.GET,"/api").permitAll()
                .antMatchers(HttpMethod.POST,"/api").hasAnyRole("MODERATOR","ADMIN")
                .antMatchers(HttpMethod.DELETE,"/api").hasRole("ADMIN")

                .and()
                .formLogin().permitAll()

                .and()
                .logout().permitAll()

                //POSTMAN
                .and()
                .csrf().disable();

    }
}
