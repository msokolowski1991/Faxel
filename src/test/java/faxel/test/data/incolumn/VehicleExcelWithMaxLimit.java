package faxel.test.data.incolumn;

import java.util.Collection;

import faxel.annotation.DataArrangementType;
import faxel.annotation.ExcelSheet;

public class VehicleExcelWithMaxLimit {

    @ExcelSheet(name = "VEHICLE", arrangement = DataArrangementType.COLUMN, startPosition = 2, maxPosition = 3)
    private Collection<Vehicle> vehicles;

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }


}
