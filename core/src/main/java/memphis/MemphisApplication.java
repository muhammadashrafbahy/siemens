package memphis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Objects;

@EnableConfigurationProperties
@EnableSwagger2
@EnableAutoConfiguration
@ComponentScan("memphis")
@SpringBootApplication(scanBasePackages={"memphis.config","memphis.domain","memphis.api"})
public class MemphisApplication {

    public static void main(String[] args) {
        String activeProfiles = System.getProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME);
        if (Objects.isNull(activeProfiles) || activeProfiles.isEmpty()) {
            throw new RuntimeException("Profile is not configured! Please setup an active profile!");
        }
        SpringApplication.run(MemphisApplication.class, args);
    }
}
