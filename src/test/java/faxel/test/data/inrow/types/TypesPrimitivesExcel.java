package faxel.test.data.inrow.types;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class TypesPrimitivesExcel {
    @ExcelSheet(name = "types", startPosition = 2)
    private Collection<TypesPrimitives> types;

    public TypesPrimitivesExcel(Collection<TypesPrimitives> types) {
        this.types = types;
    }

    public TypesPrimitivesExcel() {
    }

    public Collection<TypesPrimitives> getTypes() {
        return types;
    }

    public void setTypes(Collection<TypesPrimitives> types) {
        this.types = types;
    }
}
