package faxel.test.data.types;

import java.util.Collection;

import faxel.annotation.SheetRows;

public class TypesExcelPrimitives {
    @SheetRows(sheetName = "types", firstDataRow = 2)
    private Collection<TypesPrimitives> types;

    public TypesExcelPrimitives(Collection<TypesPrimitives> types) {
        this.types = types;
    }

    public TypesExcelPrimitives() {
    }

    public Collection<TypesPrimitives> getTypes() {
        return types;
    }

    public void setTypes(Collection<TypesPrimitives> types) {
        this.types = types;
    }
}
