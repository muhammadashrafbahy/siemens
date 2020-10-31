package memphis.loadData;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import memphis.domain.equipment;
import memphis.service.equipmentService;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Component
@Profile("dev")
public class TestDataLoader {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final equipmentService equipmentService;
    private final ObjectMapper objectMapper;

    public TestDataLoader(equipmentService equipmentService) {
        this.equipmentService = equipmentService;
        this.objectMapper = new ObjectMapper();
    }

    public void load() throws IOException {
        loadEquipments("classpath:data/equipment.json");
    }

    public void loadEquipments(String path) throws IOException {
        File file = ResourceUtils.getFile(path);
        byte[] fileContent = Files.readAllBytes(file.toPath());
        JsonNode rootNode = objectMapper.readTree(fileContent);
        rootNode.forEach(equipment -> {
            log.debug("Loading equipments json: {}", equipment);
            equipment entity = parseEquipments(equipment);
            equipmentService.createNewPayment(entity.getEquipment_id() , entity);
        });
    }

    private equipment parseEquipments(JsonNode jsonNode) {
        equipment entity = new equipment();

        entity.setEquipment_id(jsonNode.get("equipment_id").asText());
        entity.setValve(jsonNode.get("valve").asText());
        entity.setActuator(jsonNode.get("actuator").asText());
        entity.setPostioner(jsonNode.get("postioner").asText());
        entity.setSensor(jsonNode.get("sensor").asText());


        return entity;
    }

    private void clearData() {
        try {
            equipmentService.deleteAll();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

    @PostConstruct
    public void init() throws IOException {
        boolean loadData = BooleanUtils.toBoolean(System.getProperty("loadData"));
        if (loadData) {
            clearData();
            load();
        }
    }
}
