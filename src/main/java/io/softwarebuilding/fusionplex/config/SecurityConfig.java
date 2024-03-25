package io.softwarebuilding.fusionplex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain( final HttpSecurity http ) throws Exception {
        http.csrf( AbstractHttpConfigurer::disable )
                .authorizeHttpRequests( auth -> auth
                        .requestMatchers( "/script/**", "/css/**", "/error/**", "/login" ).permitAll()
                        .requestMatchers( "/admin/**" ).hasAuthority( "ROLE_ADMIN" )
                        .requestMatchers( "/genre/**", "/title/**" ).hasAuthority( "ROLE_USER" )
                        .requestMatchers( "/**" ).hasAnyAuthority( "ROLE_ADMIN", "ROLE_USER" ) )
                .formLogin( loginConfigurer -> loginConfigurer.loginPage( "/login" )
                        .defaultSuccessUrl( "/", true )
                        .permitAll())
                .logout( configurer -> configurer.logoutUrl( "/logout" )
                        .logoutSuccessUrl( "/login?logout" )
                        .deleteCookies( "JSESSIONID" )
                        .invalidateHttpSession( true ).permitAll() );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsManager userDetailsManager() {
        final UserDetails user = User.withUsername( "user" ).password( "{noop}User@1234" ).roles( "USER" ).build();
        final UserDetails admin = User.withUsername( "admin" ).password( "{noop}Admin@1234" ).roles( "ADMIN" ).build();

        return new InMemoryUserDetailsManager( user, admin );
    }

}
