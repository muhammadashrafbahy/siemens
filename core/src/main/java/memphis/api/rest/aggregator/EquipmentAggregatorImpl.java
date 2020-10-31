package memphis.api.rest.aggregator;

import memphis.domain.equipment;
import memphis.service.equipmentService;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EquipmentAggregatorImpl  implements EquipmentAggregator{
    private final equipmentService equipmentService;

    public EquipmentAggregatorImpl(memphis.service.equipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @Override
    public equipment getAnEquipmentDetails(String equipment_id) {
        return equipmentService.getequipmentDetails(equipment_id);
    }

    @Override
    public List<equipment> getAllEquipment() {
        return equipmentService.getAllEquipments();
    }

    @Override
    public void createNewEquipment(equipment equipment) {
            equipmentService.createNewPayment(equipment.getEquipment_id() ,equipment);
    }

    @Override
    public void updateAnEquipment(String equipment_id, equipment equipment) {

        equipmentService.updatePaymentDetails(equipment_id,equipment);

    }

    @Override
    public void deleteAnEquipment(String equipment_id) {
        equipmentService.deleteEquipment(equipment_id);
    }
}
