package faxel.test.data.inrow.excelsheettypes;

import java.util.List;

import faxel.annotation.ExcelSheet;

public class ListExcelSheetExcel {
    @ExcelSheet(index = 0)
    private List<AnyModel> types;

    public ListExcelSheetExcel(List<AnyModel> types) {
        this.types = types;
    }

    public ListExcelSheetExcel() {
    }

    public List<AnyModel> getTypes() {
        return types;
    }

    public void setTypes(List<AnyModel> types) {
        this.types = types;
    }
}
