package memphis.api.rest.aggregator;

import memphis.domain.equipment;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public interface EquipmentAggregator {

    equipment getAnEquipmentDetails(String equipment_id);
    List<equipment> getAllEquipment();
    void createNewEquipment(equipment equipment);
    void updateAnEquipment(String equipment_id , equipment equipment);
    void deleteAnEquipment(String equipment_id);
}
