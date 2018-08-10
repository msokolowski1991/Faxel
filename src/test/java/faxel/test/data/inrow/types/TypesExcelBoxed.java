package faxel.test.data.inrow.types;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class TypesExcelBoxed {
    @ExcelSheet(sheetName = "types", start = 2)
    private Collection<TypesBoxed> types;

    public TypesExcelBoxed(Collection<TypesBoxed> types) {
        this.types = types;
    }

    public TypesExcelBoxed() {
    }

    public Collection<TypesBoxed> getTypes() {
        return types;
    }

    public void setTypes(Collection<TypesBoxed> types) {
        this.types = types;
    }
}
