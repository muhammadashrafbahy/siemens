package memphis.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class equipmentAlreadyExist extends RuntimeException {

    private static final String errorNumber="APP1-EQUIPMENT-ERROR-003";

   private String Equipment_id;

    public equipmentAlreadyExist(String eqyipment_id){

        super(String.format("the equipment whose id is %s already exist",eqyipment_id));
        this.Equipment_id=eqyipment_id;
    }

    public String getEquipment_id(){return this.Equipment_id;}

    public String getErrorNumber() {
        return errorNumber;
    }

}

