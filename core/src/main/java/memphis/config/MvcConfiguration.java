package memphis.config;

import com.google.common.collect.Sets;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.*;

import java.util.Set;

@Configuration
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer {

    private final Environment environment;
    private final CORSConfig corsProperties;

    public MvcConfiguration(Environment environment, CORSConfig corsProperties) {
        this.environment = environment;
        this.corsProperties = corsProperties;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        if (corsProperties.isEnabled()) {
            corsProperties.getAllowedHosts()
                    .forEach(host -> registry.addMapping("/").allowedOrigins(host));
        }
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        if (isSwaggerAllowed()) {
            registry.addRedirectViewController("/v2/api-docs", "/v2/api-docs");
            registry.addRedirectViewController("/swagger-resources/configuration/ui", "/swagger-resources/configuration/ui");
            registry.addRedirectViewController("/swagger-resources/configuration/security", "/swagger-resources/configuration/security");
            registry.addRedirectViewController("/swagger-resources", "/swagger-resources");
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (isSwaggerAllowed()) {
            registry.addResourceHandler("swagger-ui.html")
                    .addResourceLocations("classpath:/META-INF/resources/");
            registry.addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
    }

    private boolean isSwaggerAllowed() {
        Set<String> activeProfiles = Sets.newHashSet(environment.getActiveProfiles());
        return activeProfiles.contains("dev") || activeProfiles.contains("stage");
    }
}