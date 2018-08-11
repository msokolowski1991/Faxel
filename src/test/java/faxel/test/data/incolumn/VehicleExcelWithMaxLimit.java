package faxel.test.data.incolumn;

import java.util.Collection;

import faxel.annotation.DataArrangementType;
import faxel.annotation.ExcelSheet;

public class VehicleExcelWithMaxLimit {

    @ExcelSheet(sheetName = "VEHICLE", arrangement = DataArrangementType.COLUMN, startIndex = 2, maxIndex = 3)
    private Collection<Vehicle> vehicles;

    public Collection<Vehicle> getVehicles() {
        return vehicles;
    }


}
