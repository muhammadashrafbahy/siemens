package memphis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "servicesettings")
public class equipmentServiceConfig {

    private String name;

    private String description;

    private String organization;

    private String website;

    private String contactPerson;

    private String contactEmail;

    private String apiVersion;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public String getApiVersion() {
        return apiVersion;
    }
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    //TODO : remove this later, this is just for testing !
    @Override
    public String toString() {
        return "PaymentServiceConfig{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", contactEmail='" + contactEmail + '\'' +
                ", apiVersion='" + apiVersion + '\'' +
                '}';
    }
}
