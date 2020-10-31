package memphis.api.rest.v1.dto.ApiResource;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import memphis.api.rest.v1.EquipmentConroller;
import memphis.domain.equipment;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;

public class equipmentsResource  extends ResourceSupport {


    private List<equipment> eqipmentList;

    public equipmentsResource(List<equipment> eqipmentList){

        this.eqipmentList=eqipmentList;

        this.add(ControllerLinkBuilder.linkTo(methodOn(EquipmentConroller.class).getALLEquipments()).withSelfRel());
    }

    public List<equipment> getEquipments(){
        return this.eqipmentList;

    }

}
