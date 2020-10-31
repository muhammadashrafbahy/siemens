package memphis.api.rest.v1

import spock.lang.Specification

class HealthControllerSpec extends Specification {

    def controller = new ApiHealth()

    def "should get health"() {
        when:
        def data = controller.health()

        then:
        data.containsKey("health")
    }
}
