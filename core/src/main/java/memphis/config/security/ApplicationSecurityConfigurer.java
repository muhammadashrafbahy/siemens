package memphis.config.security;

import memphis.security.JwtAuthenticationEntryPoint;
import memphis.security.JwtTokenAuthenticationFilter;
import memphis.security.filter.CorsFilter;
import memphis.security.pojo.JwtConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

import java.util.Objects;
import java.util.stream.Stream;


public class ApplicationSecurityConfigurer extends WebSecurityConfigurerAdapter {

    private final JwtConfig jwtConfig;
    private final JwtTokenAuthenticationFilter jwtAuthFilter;
    private final CorsFilter corsFilter;
    private final JwtAuthenticationEntryPoint authenticationEntryPoint;

    public ApplicationSecurityConfigurer(JwtConfig jwtConfig,
                                         CorsFilter corsFilter,
                                         JwtAuthenticationEntryPoint authenticationEntryPoint) {
        this.jwtConfig = jwtConfig;
        this.jwtAuthFilter = new JwtTokenAuthenticationFilter(jwtConfig);
        this.corsFilter = corsFilter;
        this.authenticationEntryPoint = authenticationEntryPoint;
    }

    @Override
    public void configure(WebSecurity web) {
        ApplicationContext applicationContext = getApplicationContext();
        Environment environment = applicationContext.getEnvironment();
        boolean ignoreSwaggerResources = Stream.of(environment.getActiveProfiles())
                .anyMatch(profile -> Objects.equals(profile, "dev") || Objects.equals(profile, "stage"));
        if (ignoreSwaggerResources) {
            web.ignoring()
                    .antMatchers("/documentation/**",
                            "/v2/api-docs",
                            "/configuration/ui",
                            "/swagger-resources/**",
                            "/configuration/**",
                            "/swagger-ui.html",
                            "/webjars/**",
                            "/swagger/**");
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers(jwtConfig.getUri()).permitAll()
                .antMatchers(HttpMethod.GET,"/equipment/health").permitAll()
                .anyRequest().authenticated().and()
                .addFilterAfter(jwtAuthFilter, SecurityContextPersistenceFilter.class)
                .addFilterBefore(corsFilter, JwtTokenAuthenticationFilter.class);
    }
}