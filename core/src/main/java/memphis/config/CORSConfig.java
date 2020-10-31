package memphis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "corssettings")
public class CORSConfig {
    private boolean enabled;

    private List<String> allowedHosts;

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public List<String> getAllowedHosts() {
        return allowedHosts;
    }

    public void setAllowedHosts(List<String> allowedHosts) {
        this.allowedHosts = allowedHosts;
    }

    @Override
    public String toString() {
        return "CORSConfiguration{" +
                "enabled=" + enabled +
                ", allowedHosts=" + allowedHosts +
                '}';
    }
}
