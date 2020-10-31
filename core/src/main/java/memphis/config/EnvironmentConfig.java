package memphis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Optional;

@Configuration
@ConfigurationProperties(prefix = "server")
public class EnvironmentConfig {

    private int port;
    private String address;


    public void setPort(int port) {
        this.port = port;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPort() {
        return port;
    }

    public String getAddress() {
        return Optional.ofNullable(address).orElse("");
    }

    @Override
    public String toString() {
        return "PlantInformationServiceConfig{" +
                "port='" + port + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
