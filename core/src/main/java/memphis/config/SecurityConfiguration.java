package memphis.config;

import memphis.config.security.ApplicationSecurityConfigurer;
import memphis.security.JwtAuthenticationEntryPoint;
import memphis.security.filter.CorsFilter;
import memphis.security.pojo.JwtConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    @Primary
    public WebSecurityConfigurerAdapter applicationSecurityConfigurer(JwtConfig jwtConfig, CorsFilter corsFilter) {
        JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
        return new ApplicationSecurityConfigurer(jwtConfig, corsFilter, jwtAuthenticationEntryPoint);
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }
}