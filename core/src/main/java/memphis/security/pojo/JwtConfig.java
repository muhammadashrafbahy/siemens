package memphis.security.pojo;

import org.springframework.beans.factory.annotation.Value;

public class JwtConfig {

    @Value("${security.jwt.uri:/v1/auth/**}")
    private String uri;

    @Value("${security.jwt.header:Authorization}")
    private String header;

    @Value("${security.jwt.prefix:Bearer }")
    private String prefix;

    @Value("${security.jwt.expiration:#{24*60*60}}")
    private int expiration;

    @Value("${security.jwt.secret:secretKey}")
    private String secret;

    @Value("${security.jwt.admin.password:pass}")
    private String adminPassword;

    public String getUri() {
        return uri;
    }


    public String getHeader() {
        return header;
    }


    public String getPrefix() {
        return prefix;
    }

    public int getExpiration() {
        return expiration;
    }

    public String getSecret() {
        return secret;
    }

    public String getAdminPassword() {
        return adminPassword;
    }

}
