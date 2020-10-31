package memphis.domain;


import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "equipment")
@JsonPropertyOrder({"equipment_id", "valve", "actuator", "positioner", "sensors"})
public class equipment {


    public equipment() {
        super();
    }


    @JsonCreator
    public equipment( @JsonProperty("equipment_id") String equipment_id,
                      @JsonProperty("valve") String valve,
                      @JsonProperty("actuator") String actuator,
                      @JsonProperty("positioner")  String postioner,
                      @JsonProperty("sensor") String sensor) {
        this.equipment_id = equipment_id;
        this.valve = valve;
        this.actuator = actuator;
        this.postioner = postioner;
        this.sensor = sensor;
    }

    @Id
    @Column(unique = true, nullable = false)
   private String equipment_id ;

    @Column( nullable = false)
    private String valve;


    @Column(nullable = false)
    private String actuator;


    @Column( nullable = false)
    private String postioner;

    @Column( nullable = false)
    private String sensor;

    public String getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(String equipment_id) {
        this.equipment_id = equipment_id;
    }

    public void setValve(String valve) {
        this.valve = valve;
    }

    public void setActuator(String actuator) {
        this.actuator = actuator;
    }

    public void setPostioner(String postioner) {
        this.postioner = postioner;
    }

    public void setSensor(String sensor) {
        this.sensor = sensor;
    }

    public String getValve() {
        return valve;
    }

    public String getActuator() {
        return actuator;
    }

    public String getPostioner() {
        return postioner;
    }

    public String getSensor() {
        return sensor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        equipment equipment= (equipment) o;
        return Objects.equals(equipment_id , equipment.equipment_id); }

    @Override
    public int hashCode() {

        return Objects.hash(equipment_id);
    }

    @Override
    public String toString() {
        return "equipment{" +
                "equipment_id=" + equipment_id+
                ", valve='" + valve + '\'' +
                ", actuator='" + actuator+ '\'' +
                ", positiner=" + postioner +
                ", sensor=" + sensor+
                '}';
    }
}
