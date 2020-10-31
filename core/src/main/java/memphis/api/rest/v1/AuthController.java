package memphis.api.rest.v1;

import memphis.api.rest.v1.dto.auth.AuthenticationRequest;
import memphis.api.rest.v1.dto.auth.AuthenticationResponse;
import memphis.service.auth.AuthService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController()
@RequestMapping( "/v1/auth/")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping
    public ResponseEntity<AuthenticationResponse> authorize(@RequestBody @Valid AuthenticationRequest authenticationRequest) {
        AuthenticationResponse response = authService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }
}
