package memphis.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = {
        "classpath:config/${spring.profiles.active}/equipment.properties",
        "file:${app.properties.override.path}/equipment.properties"
}, ignoreResourceNotFound = true)
public class PropertiesConfiguration {
}
