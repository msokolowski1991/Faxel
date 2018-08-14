package faxel.test.data.inrow.fail;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class WrongTypeExcel {
    @ExcelSheet(name = "unknown")
    private Collection<WrongTypeModel> models;

    public Collection<WrongTypeModel> getModels() {
        return models;
    }
}
