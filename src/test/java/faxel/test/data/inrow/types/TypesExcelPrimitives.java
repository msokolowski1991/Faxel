package faxel.test.data.inrow.types;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class TypesExcelPrimitives {
    @ExcelSheet(sheetName = "types", startIndex = 2)
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
