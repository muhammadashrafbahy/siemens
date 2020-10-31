package memphis.api.rest.v1;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import memphis.api.rest.aggregator.EquipmentAggregator;
import memphis.api.rest.v1.dto.ApiResource.equipmentResource;
import memphis.api.rest.v1.dto.ApiResource.equipmentsResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import memphis.domain.equipment;

    import javax.validation.Valid;


@RestController
@RequestMapping("/equipment")
@Api(description = "manage and execute operations on equipment")
public class EquipmentConroller {


    private final EquipmentAggregator equipmentAggregator;

    public EquipmentConroller(EquipmentAggregator equipmentAggregator){
        this.equipmentAggregator =equipmentAggregator;
    }

    @Transactional
    @ApiOperation(value = "Get ALl Equipments")
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public equipmentsResource getALLEquipments(){

        List<equipment> result =this.equipmentAggregator.getAllEquipment();

        return new equipmentsResource(result);
    }

    @Transactional
    @ApiOperation(value = "Get a equipment details according to given equipmentId" )
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public equipmentResource getEquipmentDetails(@PathVariable("id") String id){

        equipment result = this.equipmentAggregator.getAnEquipmentDetails(id);

        return new equipmentResource(result);
    }


    @Transactional
    @ApiOperation(value = "Update Equipment according to equipmentId")
    @PutMapping(value = "/{id}" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateEquipment(@PathVariable("id") String id , @Valid @RequestBody equipment equipment ){

        this.equipmentAggregator.updateAnEquipment(id ,equipment);

        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .buildAndExpand()
                    .toUri();
        return  ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).build();

    }

    @Transactional
    @ApiOperation(value = "Create new Equipment ")
    @PostMapping(value = "/" , produces = MediaType.APPLICATION_JSON_VALUE , consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createEquipment (@Valid @RequestBody equipment equipment  ){

        this.equipmentAggregator.createNewEquipment(equipment);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .replacePath("/app1/equipment")
                .path("/" + equipment.getEquipment_id())
                .buildAndExpand().toUri();

        return  ResponseEntity.created(location).contentType(MediaType.APPLICATION_JSON).build();

    }

    @Transactional
    @ApiOperation(value = "Delete Equipment according to equipmentId")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity deleteEquipment(@PathVariable("id") String id  ){

        this.equipmentAggregator.deleteAnEquipment(id);


        return  ResponseEntity.noContent().build();

    }
}
