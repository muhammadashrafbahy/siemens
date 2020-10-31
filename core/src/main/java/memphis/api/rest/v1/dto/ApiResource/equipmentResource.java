package memphis.api.rest.v1.dto.ApiResource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import memphis.api.rest.v1.EquipmentConroller;
import memphis.domain.equipment;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class equipmentResource extends ResourceSupport {

    private memphis.domain.equipment equipment;


    public equipmentResource(equipment equipment){

        this.equipment=equipment;
        this.add(ControllerLinkBuilder.linkTo(methodOn(EquipmentConroller.class)
                .getEquipmentDetails(this.equipment.getEquipment_id()))
                .withSelfRel());

    }

    public equipment getEquipment(){
        return this.equipment;
    }

}
