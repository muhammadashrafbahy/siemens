package memphis.service.auth;

import memphis.api.rest.v1.dto.auth.AuthenticationRequest;
import memphis.api.rest.v1.dto.auth.AuthenticationResponse;
import memphis.security.generator.AuthTokenGenerator;
import memphis.security.pojo.JwtConfig;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthServiceImpl implements AuthService {

    private final JwtConfig jwtConfig;
    private final AuthTokenGenerator authTokenGenerator;

    public AuthServiceImpl(JwtConfig jwtConfig, AuthTokenGenerator authTokenGenerator) {
        this.jwtConfig = jwtConfig;
        this.authTokenGenerator = authTokenGenerator;
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        boolean equals = Objects.equals(authenticationRequest.getPassword(), jwtConfig.getAdminPassword());
        if (equals) {
            String token = authTokenGenerator.generate();
            return new AuthenticationResponse(token, jwtConfig.getExpiration());
        } else {
            throw new BadCredentialsException("Wrong auth data");
        }
    }
}
