package memphis.service;


import org.springframework.beans.factory.annotation.Autowired;
import memphis.domain.equipment;
import memphis.exception.equipmentAlreadyExist;
import memphis.exception.equipmentNotFound;
import memphis.reprositery.equiomentResprositery;


import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import memphis.service.validation.equipmentValidator;

import java.util.List;


@Service
public class equipmentService {
    private static final ModelMapper modelMapper =new ModelMapper();

    private equiomentResprositery equiomentResprositery;

    private memphis.service.validation.equipmentValidator equipmentValidator;

    @Autowired
    public  equipmentService(equiomentResprositery equiomentResprositery ,equipmentValidator equipmentValidator ){
        this.equiomentResprositery=equiomentResprositery;
        this.equipmentValidator=equipmentValidator;

    }

    /**
     * returns a list of all equipments

     * @return a list of all equipments in the system
     */

    public List<equipment> getAllEquipments (){
        return equiomentResprositery.findAll();
    }

    /**
     * returns the details of a given equipment
     * @param equipment_id the String of the equipment to update
     * @return equipment details
     */
    public equipment getequipmentDetails(String equipment_id) {
        try {
            return this.equipmentValidator.getValidEquipment(equipment_id);
        } catch (Exception e) {
            throw new equipmentNotFound(equipment_id);
        }
    }

    /**
     * deletes an existing equipment
     * @param equipment_id the String of the equipment to update
     */
    public void deleteEquipment(String equipment_id) {

        if(this.equipmentValidator.getValidEquipment(equipment_id) != null) {
            equipment equipment = equipmentValidator.getValidEquipment(equipment_id);
                equiomentResprositery.delete(equipment);

        }else {
            throw new equipmentNotFound(equipment_id);
        }
    }


    /**
     * updates an existing equipment
     * @param equipment_id the String  of the equipment to update
     * @param updatedEquipment the payload of the equipment to update

     */
    public void updatePaymentDetails(String equipment_id, equipment updatedEquipment) {


        updatedEquipment.setEquipment_id(equipment_id);

        equipment equipment= equipmentValidator.getValidEquipment(equipment_id);
        modelMapper.map(updatedEquipment, equipment);
        this.equiomentResprositery.save(equipment);
    }


    /**
     * creates a new equipment
     * @param equipment the equipment to be created
     * @return a newly created equipment
     */
    public equipment createNewPayment(String equipment_id, equipment equipment) {


        if ( !equipmentValidator.CheckEquipmentExistforCreation(equipment_id)) {
            throw new equipmentAlreadyExist(equipment_id);
        }

        return this.equiomentResprositery.save(equipment);
    }

    public void deleteAll(){
        this.equiomentResprositery.deleteAll();
    }


}
