package faxel.test.data.types;

import java.util.Collection;

import faxel.annotation.SheetRows;

public class TypesExcelBoxed {
    @SheetRows(sheetName = "types", firstDataRow = 2)
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
