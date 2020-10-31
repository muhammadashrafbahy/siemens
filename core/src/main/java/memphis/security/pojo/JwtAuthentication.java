package memphis.security.pojo;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class JwtAuthentication extends AbstractAuthenticationToken {

    private final String jwtToken;

    public JwtAuthentication(String token) {
        super(null);
        this.jwtToken = token;
        setAuthenticated(true);
    }

    @Override
    public String getCredentials() {
        return jwtToken;
    }

    @Override
    public String getPrincipal() {
        return jwtToken;
    }
}

