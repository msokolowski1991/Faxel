package faxel.test.data.inrow.excelsheettypes;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class CollectionExcelSheetExcel {
    @ExcelSheet(index = 0, startPosition = 2)
    private Collection<AnyModel> types;

    public CollectionExcelSheetExcel(Collection<AnyModel> types) {
        this.types = types;
    }

    public CollectionExcelSheetExcel() {
    }

    public Collection<AnyModel> getTypes() {
        return types;
    }

    public void setTypes(Collection<AnyModel> types) {
        this.types = types;
    }
}
