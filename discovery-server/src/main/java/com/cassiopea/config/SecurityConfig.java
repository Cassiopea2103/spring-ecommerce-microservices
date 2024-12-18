package com.cassiopea.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${eureka.username}")
    private String username ;
    @Value ( "${eureka.password}")
    private String password ;

    // Filter configuration :
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http ) throws Exception {
        http.csrf ( AbstractHttpConfigurer::disable ) ;

        http.authorizeHttpRequests(
                requests -> requests.anyRequest().authenticated ()
        );

        http.httpBasic( Customizer.withDefaults() ) ;

        return http.build () ;
    }

    // In memory user details manager :
    @Bean
    public InMemoryUserDetailsManager userDetailsManager () {
        UserDetails user = User.withUsername ( username )
                .password ( passwordEncoder().encode ( password ) )
                .roles ( "USER" )
                .build () ;

        return new InMemoryUserDetailsManager( user ) ;
    }

    @Bean
    public PasswordEncoder passwordEncoder () {
        return new BCryptPasswordEncoder( 10 ) ;
    }

}
