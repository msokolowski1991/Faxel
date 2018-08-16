package faxel.test.data.inrow.excelsheettypes;

import java.util.HashSet;

import faxel.annotation.ExcelSheet;

public class HashSetExcelSheetExcel {
    @ExcelSheet(index = 0)
    private HashSet<AnyModel> types;

    public HashSetExcelSheetExcel(HashSet<AnyModel> types) {
        this.types = types;
    }

    public HashSetExcelSheetExcel() {
    }

    public HashSet<AnyModel> getTypes() {
        return types;
    }

    public void setTypes(HashSet<AnyModel> types) {
        this.types = types;
    }
}
