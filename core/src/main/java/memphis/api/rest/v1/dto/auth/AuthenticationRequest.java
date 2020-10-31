package memphis.api.rest.v1.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public class AuthenticationRequest {

    @NotNull
    private final String password;

    public AuthenticationRequest(@JsonProperty("password") String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
