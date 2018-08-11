package faxel.test.data.inrow.fail;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class ExcelSheetWithoutNameOrIndexExcel {
    @ExcelSheet()
    private Collection<UnknownTypeModel> models;

    public Collection<UnknownTypeModel> getModels() {
        return models;
    }
}
