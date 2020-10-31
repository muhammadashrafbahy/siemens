package memphis.service


import memphis.domain.equipment
import spock.lang.Specification
import memphis.reprositery.equiomentResprositery
import memphis.service.validation.equipmentValidator
import  memphis.exception.equipmentNotFound
import  memphis.exception.equipmentAlreadyExist

class   EquipmentSerivceTest extends Specification {

    def EquipmenResprository = Mock(equiomentResprositery)
    def EquipmentValidator = Mock(equipmentValidator)

    def EquipmentService = new equipmentService(EquipmenResprository , EquipmentValidator)


    def "should get all equipments "(){
        setup:
             def returned = new ArrayList()

        when:
              def result =EquipmentService.getAllEquipments()


        then:
              1 * EquipmenResprository.findAll() >> returned
              result == returned

    }

    def "should get an equipments "(){

        setup:
        def equipment_id = "22Y2071"
        def returned = new equipment(equipment_id : equipment_id)

        when:
        def result =EquipmentService.getequipmentDetails(equipment_id)


        then:
        1 * EquipmentValidator.getValidEquipment(equipment_id)>> returned
        result == returned

    }
    def "shouldnt get non exist equipment"(){

        setup:
        def equipment_id = "xxxx"
        def returned = new equipment(equipment_id : equipment_id)

        when:
        EquipmentService.getequipmentDetails(equipment_id)


        then:

        0 * EquipmenResprository.findById(equipment_id).orElse(null)>> returned


    }
    def "should delete an equipments "(){

        setup:
        def equipment_id = "22Y2071"

        when:
        EquipmentService.deleteEquipment(equipment_id)


        then:
         thrown(equipmentNotFound)


    }
    def "shouldnt delete non exist equipment"(){

        setup:
        def equipment_id = "xxxx"

        when:
        EquipmentService.deleteEquipment(equipment_id)


        then :
        0 * EquipmenResprository.delete(equipment_id )
        thrown(equipmentNotFound)


    }

    def "shouldnt create already exist equipments "(){

        setup:
        def equipment_id = "22Y2071"
        def equipments= new equipment(equipment_id : equipment_id)

        when:
        EquipmentService.createNewPayment(equipment_id , equipments)


        then:

        1 * EquipmentValidator.CheckEquipmentExistforCreation(equipment_id)
        thrown(equipmentAlreadyExist)
        and:
        0 *EquipmenResprository.save(equipments)

    }
    
}
