package faxel.test.data.inrow.excelsheettypes;

import faxel.annotation.ExcelSheet;

public class CustomImplementationExcelSheetExcel {
    @ExcelSheet(index = 0)
    private MyCollection<AnyModel> types;

    public CustomImplementationExcelSheetExcel(MyCollection<AnyModel> types) {
        this.types = types;
    }

    public CustomImplementationExcelSheetExcel() {
    }

    public MyCollection<AnyModel> getTypes() {
        return types;
    }

    public void setTypes(MyCollection<AnyModel> types) {
        this.types = types;
    }


}
