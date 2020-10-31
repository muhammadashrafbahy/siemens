package memphis.api.rest.v1.dto.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationResponse {

    private final String token;
    private final long expiration;

    @JsonCreator
    public AuthenticationResponse(@JsonProperty("token") String token,
                                  @JsonProperty("expiration") long expiration) {
        this.token = token;
        this.expiration = expiration;
    }

    public String getToken() {
        return token;
    }

    public long getExpiration() {
        return expiration;
    }
}
