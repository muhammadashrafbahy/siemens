package memphis.StepDefination;

import cucumber.api.DataTable;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import gherkin.formatter.model.DataTableRow;
import memphis.domain.equipment;
import memphis.service.equipmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;

import java.sql.SQLException;
import java.util.List;

@ActiveProfiles("config/test")
public class GivenStepDefination  {

    private equipmentService equipmentservice;

    @Autowired
    public GivenStepDefination(  equipmentService equipmentservice ) {

        this.equipmentservice = equipmentservice;

    }
    @Before
    public void truncateDatabase() throws SQLException {
        System.out.println("before implementation");

            equipmentservice.deleteAll();
    }

    @Given("^the following equipments$")
    public void savePaymentListToDatabase(DataTable equipments) {

        System.out.println("store equipment to the database");


        //get all records from the datatable
        List<DataTableRow> dataTablesRecords = equipments.getGherkinRows();

        for(int i = 0; i < dataTablesRecords.size(); i++){
            if(i == 0)
                continue;
            //get record by record
            DataTableRow dataTableRecord = dataTablesRecords.get(i);
            //get each value in each record
            List<String> cellsPerRow = dataTableRecord.getCells();
            String equipment_id = cellsPerRow.get(0);
            String valve = cellsPerRow.get(1);
            String actuator = cellsPerRow.get(2);
            String positioner = cellsPerRow.get(3);
            String sensors = cellsPerRow.get(4);

            equipment equipment = new equipment(equipment_id , valve ,actuator ,positioner ,sensors);


            this.equipmentservice.createNewPayment(equipment_id ,equipment );


        }
    }
}
