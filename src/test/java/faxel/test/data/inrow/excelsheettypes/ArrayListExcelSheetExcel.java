package faxel.test.data.inrow.excelsheettypes;

import java.util.ArrayList;

import faxel.annotation.ExcelSheet;

public class ArrayListExcelSheetExcel {
    @ExcelSheet(index = 0)
    private ArrayList<AnyModel> types;

    public ArrayListExcelSheetExcel(ArrayList<AnyModel> types) {
        this.types = types;
    }

    public ArrayListExcelSheetExcel() {
    }

    public ArrayList<AnyModel> getTypes() {
        return types;
    }

    public void setTypes(ArrayList<AnyModel> types) {
        this.types = types;
    }
}
