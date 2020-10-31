package memphis.api.rest.v1


import memphis.domain.equipment
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import org.springframework.web.util.WebUtils
import spock.lang.Specification
import org.springframework.http.*

import javax.servlet.http.HttpServletRequest
import memphis.api.rest.aggregator.EquipmentAggregator

class EquipmentControllerTest  extends Specification{

    def request = Mock(HttpServletRequest) {
        getRequestURL() >> new StringBuffer("http://localhost:10000")
        getHeaderNames() >> Collections.emptyEnumeration()
        getAttribute(WebUtils.INCLUDE_CONTEXT_PATH_ATTRIBUTE) >> "http://localhost:10000"
        getAttribute(WebUtils.INCLUDE_REQUEST_URI_ATTRIBUTE) >> "http://localhost:10000"
        getAttribute(WebUtils.INCLUDE_SERVLET_PATH_ATTRIBUTE) >> "http://localhost:1000"
    }

    def aggregator = Mock(EquipmentAggregator.class)
    def controller = new EquipmentConroller(aggregator)

    def "should get all equipments"(){
        setup:
        def returned = new ArrayList()

        when:
        def result =controller.getALLEquipments()


        then:
        1 * aggregator.getAllEquipment() >> returned
        result.getEquipments() == returned


    }
    def "should get an equipments "(){

        setup:
        def equipment_id = "22Y2071"
        def returned = new equipment(equipment_id : equipment_id)

        when:
        def result =controller.getEquipmentDetails(equipment_id)


        then:
        1 * aggregator.getAnEquipmentDetails(equipment_id)>> returned
        result.getEquipment() == returned

    }
    def "should add the equipment"() {
        setup:
        def equipment_id = "new"
        def equipments= new equipment( equipment_id,"new", "new" ,"new","new")
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request))

        def expectedResult = ResponseEntity.created( ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .replacePath("/app1/equipment")
                .path("/" + equipment_id)
                .buildAndExpand().toUri()).contentType(MediaType.APPLICATION_JSON).build()

        when:
        def result = controller.createEquipment(equipments)

        then:
        1 * aggregator.createNewEquipment(equipments)
        expectedResult == result
    }
    def "should delete the equipment"() {
        setup:
        def equipment_id = "22Y2071"

        when:
        def result = controller.deleteEquipment(equipment_id)

        then:
        1 * aggregator.deleteAnEquipment(equipment_id)
        result == ResponseEntity.noContent().build()
    }
}
