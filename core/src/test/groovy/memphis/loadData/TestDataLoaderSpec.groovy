package memphis.loadData
import memphis.reprositery.equiomentResprositery
import memphis.service.equipmentService
import spock.lang.Specification
import memphis.service.validation.equipmentValidator
class TestDataLoaderSpec extends Specification {

    def validator =  Mock(equipmentValidator)
    def reprositery = Mock(equiomentResprositery)
    def service = new equipmentService(reprositery ,validator)
    def loader = Spy(TestDataLoader, constructorArgs: [service]) {
        loadEquipments(_) >> null
    }

    def "should load test data"() {
        when:
        loader.load()

        then:
        1 * loader.loadEquipments(_) >> null
    }

}
