package faxel.test.data.inrow.fail;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class UnknownSheetNameExcel {
    @ExcelSheet(name = "misspell")
    private Collection<UnknownTypeModel> models;

    public Collection<UnknownTypeModel> getModels() {
        return models;
    }
}
