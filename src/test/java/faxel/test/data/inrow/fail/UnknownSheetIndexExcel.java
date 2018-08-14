package faxel.test.data.inrow.fail;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class UnknownSheetIndexExcel {
    @ExcelSheet(index = 1)
    private Collection<UnknownTypeModel> models;

    public Collection<UnknownTypeModel> getModels() {
        return models;
    }
}
