package faxel.test.data.inrow.fail;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class UnknownTypeExcel {
    @ExcelSheet(name = "unknown")
    private Collection<UnknownTypeModel> models;

    public Collection<UnknownTypeModel> getModels() {
        return models;
    }
}
