package faxel.test.data.inrow.types;

import java.util.Collection;

import faxel.annotation.ExcelSheet;

public class TypesBoxedIndexdSheetExcel {
    @ExcelSheet(index = 0, startPosition = 2)
    private Collection<TypesBoxed> types;

    public TypesBoxedIndexdSheetExcel(Collection<TypesBoxed> types) {
        this.types = types;
    }

    public TypesBoxedIndexdSheetExcel() {
    }

    public Collection<TypesBoxed> getTypes() {
        return types;
    }

    public void setTypes(Collection<TypesBoxed> types) {
        this.types = types;
    }
}
