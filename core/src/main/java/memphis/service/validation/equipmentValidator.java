package memphis.service.validation;


import org.springframework.beans.factory.annotation.Autowired;
import memphis.domain.equipment;
import memphis.exception.equipmentNotFound;
import memphis.reprositery.equiomentResprositery;
import org.springframework.stereotype.Component;

@Component
public class equipmentValidator  {


    private equiomentResprositery equiomentResprositery;

    @Autowired
    public equipmentValidator (equiomentResprositery equiomentResprositery){
        this.equiomentResprositery=equiomentResprositery;
    }


    /**
     * returns a valid equipmentfrom the database
     * @param equipment_id the given equipment_id
     * @return the equipment object
     */

    public equipment getValidEquipment(String equipment_id){

        equipment equipment = equiomentResprositery.findById(equipment_id).orElse(null);

        if(equipment==null){

            throw new equipmentNotFound(equipment_id);
        }
        return equipment;
    }

    public boolean CheckEquipmentExistforCreation(String equipment_id){

        equipment equipment = equiomentResprositery.findById(equipment_id).orElse(null);

        if(equipment==null){

            return  true;
        }
        return false;
    }

}
