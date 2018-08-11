package faxel.test.data.inrow.fail;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class NoPublicConstructorExcel {
    @ExcelSheet(sheetName = "unknown")
    private Collection<NoPublicConstructorModel> models;
}
