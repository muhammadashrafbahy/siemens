package memphis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.hal.HalConfiguration;

@Configuration
public class AppConfiguration {

    @Bean
    public HalConfiguration halConfiguration() {
        return new HalConfiguration();
    }
}
