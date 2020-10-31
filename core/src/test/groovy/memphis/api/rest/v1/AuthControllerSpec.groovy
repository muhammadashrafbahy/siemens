package memphis.api.rest.v1

import memphis.api.rest.v1.dto.auth.AuthenticationRequest
import memphis.api.rest.v1.dto.auth.AuthenticationResponse
import memphis.service.auth.AuthService
import org.springframework.http.ResponseEntity
import spock.lang.Specification

class AuthControllerSpec extends Specification {

    def authService = Mock(AuthService)
    def controller = new AuthController(authService)

    def "user should authorize"() {
        setup:
        def authRequest = new AuthenticationRequest("pass")
        def authResponse = new AuthenticationResponse("test", 1000L)

        when:
        def response = controller.authorize(authRequest)

        then:
        1 * authService.authenticate(authRequest) >> authResponse
        response == ResponseEntity.ok(authResponse)
    }
}
