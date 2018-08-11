package faxel.test.data.inrow.fail;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class NoPublicConstructorExcel {
    @ExcelSheet(name = "unknown")
    private Collection<NoPublicConstructorModel> models;
}
