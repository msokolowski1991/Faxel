package faxel.definition;

import java.lang.reflect.Field;
import java.util.Collection;

import faxel.annotation.Sheet;

final class SheetDefinition {
    private final faxel.annotation.Sheet sheet;
    private final Field containingField;
    private final Collection<ColumnDefinition> columnDefinitions;

    SheetDefinition(Sheet sheet, Field containingField, Collection<ColumnDefinition> columnDefinitions) {
        this.sheet = sheet;
        this.containingField = containingField;
        this.columnDefinitions = columnDefinitions;
    }
}
