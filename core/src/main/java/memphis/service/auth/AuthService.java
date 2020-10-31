package memphis.service.auth;

import memphis.api.rest.v1.dto.auth.AuthenticationRequest;
import memphis.api.rest.v1.dto.auth.AuthenticationResponse;

public interface AuthService {

    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);
}
