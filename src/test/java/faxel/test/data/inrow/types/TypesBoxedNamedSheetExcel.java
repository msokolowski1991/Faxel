package faxel.test.data.inrow.types;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class TypesBoxedNamedSheetExcel {
    @ExcelSheet(name = "types", startPosition = 2)
    private Collection<TypesBoxed> types;

    public TypesBoxedNamedSheetExcel(Collection<TypesBoxed> types) {
        this.types = types;
    }

    public TypesBoxedNamedSheetExcel() {
    }

    public Collection<TypesBoxed> getTypes() {
        return types;
    }

    public void setTypes(Collection<TypesBoxed> types) {
        this.types = types;
    }
}
