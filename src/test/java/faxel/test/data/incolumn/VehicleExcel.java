package faxel.test.data.incolumn;

import faxel.annotation.DataArrangementType;
import faxel.annotation.ExcelSheet;

import java.util.Collection;

public class VehicleExcel {

    @ExcelSheet(name = "Vehicle", arrangement = DataArrangementType.COLUMN, startPosition = 2)
    private Collection<Vehicle> vehicles;

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }


}
