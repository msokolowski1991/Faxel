package faxel.test.data.inrow.excelsheettypes;

import java.util.Set;

import faxel.annotation.ExcelSheet;

public class SetExcelSheetExcel {
    @ExcelSheet(index = 0, startPosition = 2)
    private Set<AnyModel> types;

    public SetExcelSheetExcel(Set<AnyModel> types) {
        this.types = types;
    }

    public SetExcelSheetExcel() {
    }

    public Set<AnyModel> getTypes() {
        return types;
    }

    public void setTypes(Set<AnyModel> types) {
        this.types = types;
    }
}
